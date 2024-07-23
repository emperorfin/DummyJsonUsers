package emperorfin.android.dummyjsonusers.ui.screen.users.stateholders

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emperorfin.android.dummyjsonusers.R
import emperorfin.android.dummyjsonusers.di.DefaultDispatcher
import emperorfin.android.dummyjsonusers.di.IoDispatcher
import emperorfin.android.dummyjsonusers.domain.datalayer.dao.IUserDao
import emperorfin.android.dummyjsonusers.domain.datalayer.repository.IUserRepository
import emperorfin.android.dummyjsonusers.domain.exception.UserFailure
import emperorfin.android.dummyjsonusers.domain.model.user.UserModel
import emperorfin.android.dummyjsonusers.domain.model.user.UserModelMapper
import emperorfin.android.dummyjsonusers.domain.uilayer.event.input.None
import emperorfin.android.dummyjsonusers.domain.uilayer.event.input.UserParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.ResultData
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.succeeded
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.Params
import emperorfin.android.dummyjsonusers.ui.model.user.UserUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.UserUiModelMapper
import emperorfin.android.dummyjsonusers.ui.util.InternetConnectivityUtil.hasInternetConnection
import emperorfin.android.dummyjsonusers.ui.util.WhileUiSubscribed
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.properties.Delegates


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */


@HiltViewModel
class UsersViewModel @Inject constructor(
    val application: Application,
    private val userRepository: IUserRepository,
    private val userModelMapper: UserModelMapper,
    private val userUiModelMapper: UserUiModelMapper,
    @IoDispatcher private val coroutineDispatcherIo: CoroutineDispatcher,
    @DefaultDispatcher private val coroutineDispatcherDefault: CoroutineDispatcher,
) : ViewModel() {

    companion object {
        private const val NUM_OF_USERS_MINUS_1: Int = -1
        private const val NUM_OF_USERS_0: Int = 0
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    val errorMessage: StateFlow<Int?> = _errorMessage

    private val _messageSnackBar: MutableStateFlow<Int?> = MutableStateFlow(null)
    val messageSnackBar: StateFlow<Int?> = _messageSnackBar

    private val _users: MutableStateFlow<ResultData<List<UserUiModel>>> = MutableStateFlow(ResultData.Loading)
    val users: StateFlow<ResultData<List<UserUiModel>>> = _users

    private val _usersOld: MutableStateFlow<ResultData<List<UserUiModel>>> = MutableStateFlow(ResultData.Success(emptyList()))

    private val _newUsersLength: MutableStateFlow<Int> = MutableStateFlow(IUserDao.REMOTE_NUM_OF_USERS_TO_SKIP)
    val newUsersLength: StateFlow<Int> = _newUsersLength

    private val _totalRetrievedUsersLength: MutableStateFlow<Int> = MutableStateFlow(0)
    val totalRetrievedUsersLength: StateFlow<Int> = _totalRetrievedUsersLength

    init {

        loadUsers(
            params = UserParams(othersArgs = mapOf("skip" to "0")),
            isRefresh = false
//            isRefresh = true
        )
    }

    val uiState: StateFlow<UsersUiState> = combine(
        isLoading, errorMessage, users, messageSnackBar
    ) { isLoading, errorMessage, users, messageSnackBar ->

        when (users) {

            ResultData.Loading -> {
                UsersUiState(isLoading = true)
            }
            is ResultData.Error -> {
                UsersUiState(
//                    errorMessage = (users.failure as UserFailure.GetUserRepositoryError).message,
//                    errorMessage = (users.failure as UserFailure.GetUserRemoteError).message,
                    errorMessage = (users.failure as UserFailure).message,
                    messageSnackBar = messageSnackBar
                )
            }
            is ResultData.Success<List<UserUiModel>> -> {

                val _users: List<UserUiModel> = users.data

                val usersSorted = _users.sortedBy {
                    it.firstName
                }

                UsersUiState(
//                    users = usersSorted,
                    users = _users,
                    isLoading = isLoading,
//                    errorMessage = errorMessage,
                    errorMessage = null,
                    messageSnackBar = messageSnackBar
                )
            }
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = UsersUiState(isLoading = true)
        )

    fun snackBarMessageShown() {
        _messageSnackBar.value = null
    }

    fun fetchNextUserPage() {

        val remainingRetrievableRemoteUsers: Int = IUserDao.REMOTE_TOTAL_USERS - totalRetrievedUsersLength.value

        if (remainingRetrievableRemoteUsers < 1) {
            Toast.makeText(
                application,
                R.string.message_max_num_of_users_retrieved,
                Toast.LENGTH_LONG
            ).show()

            return
        }

        if (!isLoading.value) {

            val skip: Int = newUsersLength.value

            loadUsers(
                params = UserParams(othersArgs = mapOf("skip" to skip.toString())),
                isRefresh = false
            )
        }
    }

    private fun getUsersViaRepository1(
        params: Params,
        isRefresh: Boolean = false,
    ) = viewModelScope.launch(context = coroutineDispatcherIo) {

        _users.value = ResultData.Loading

        val usersResultData: ResultData<List<UserModel>> =
            userRepository.getUsers(params = params, forceUpdate = isRefresh)

        if (usersResultData.succeeded) {
            val usersEntity = (usersResultData as ResultData.Success).data

            val usersUiModel: List<UserUiModel> = usersEntity.map {
                userModelMapper.transform(it)
            }.map {
                userUiModelMapper.transform(it)
            }

            _users.value = ResultData.Success(data = usersUiModel)

        } else {
            val error: ResultData.Error = (usersResultData as ResultData.Error)
            _users.value = error

        }

    }

    private fun getUsersViaRepository(
        params: Params,
        isRefresh: Boolean = false,
    ) = viewModelScope.launch(context = coroutineDispatcherIo) {

        val usersOld = (_usersOld.value as ResultData.Success).data
        val usersNew = mutableListOf<UserUiModel>()

        _users.value = ResultData.Loading

        val usersResultData: ResultData<List<UserModel>> =
            userRepository.getUsers(params = params, forceUpdate = isRefresh)

        if (usersResultData.succeeded) {
            val usersEntity = (usersResultData as ResultData.Success).data

            val usersUiModel: List<UserUiModel> = usersEntity.map {
                userModelMapper.transform(it)
            }.map {
                userUiModelMapper.transform(it)
            }

            usersNew.addAll(usersOld)
            usersNew.addAll(usersUiModel)

            _newUsersLength.value = usersNew.size
            _totalRetrievedUsersLength.value += usersNew.size

//            _users.value = ResultData.Success(data = usersUiModel)
            _usersOld.value = ResultData.Success(data = usersNew)
            _users.value = ResultData.Success(data = usersNew)

        } else {
            val error: ResultData.Error = (usersResultData as ResultData.Error)
            _users.value = error

        }

    }

    private fun searchUsers(params: UserParams, isRefresh: Boolean = true) {
        getUsersViaRepository(params = params, isRefresh = isRefresh)
    }

    private fun getAllUsers(params: None = None(), isRefresh: Boolean = false) {
        getUsersViaRepository(params = params, isRefresh = isRefresh)
    }

    /**
     * To get all users, pass None() as params.
     * To search for users, pass UserParams() as params.
     */
    fun loadUsers(params: Params, isRefresh: Boolean){
        viewModelScope.launch/*(context = coroutineDispatcherIo)*/ {

            var usersCount by Delegates.notNull<Int>()

//            val usersCountDataResultEvent = userRepository.countAllUsers(params = params)
            val usersCountDataResultEvent = userRepository.countAllUsers(params = None())

            usersCount = if (usersCountDataResultEvent.succeeded)
                (usersCountDataResultEvent as ResultData.Success).data
            else
                NUM_OF_USERS_MINUS_1

            if (usersCount > NUM_OF_USERS_0 || isRefresh){

                if (hasInternetConnection(application)){

                    searchUsers(
                        params = params as UserParams,
                        isRefresh = true,
                    )
                } else {

                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            application,
                            R.string.message_no_internet_loading_cached_users,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    getAllUsers(
//                        params = params as None,
                        params = None(),
                        isRefresh = false
                    )
                }
            } else {

                if (hasInternetConnection(application)){
                    searchUsers(
                        params = params as UserParams,
                        isRefresh = true
                    )
                } else {

                    _messageSnackBar.value = R.string.message_no_internet_connectivity

                    _users.value = ResultData.Error(
                        failure = UserFailure.UserRemoteError(
                            message = R.string.message_no_internet_connectivity
                        )
                    )
                }
            }

        }
    }

}