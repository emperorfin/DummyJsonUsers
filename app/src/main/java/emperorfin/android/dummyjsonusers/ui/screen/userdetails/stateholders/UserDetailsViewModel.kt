package emperorfin.android.dummyjsonusers.ui.screen.userdetails.stateholders

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emperorfin.android.dummyjsonusers.R
import emperorfin.android.dummyjsonusers.di.DefaultDispatcher
import emperorfin.android.dummyjsonusers.di.IoDispatcher
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
class UserDetailsViewModel @Inject constructor(
    val application: Application,
    private val userRepository: IUserRepository,
    private val userModelMapper: UserModelMapper,
    private val userUiModelMapper: UserUiModelMapper,
    @IoDispatcher private val coroutineDispatcherIo: CoroutineDispatcher,
    @DefaultDispatcher private val coroutineDispatcherDefault: CoroutineDispatcher,
) : ViewModel() {

    companion object {
        private const val NUM_OF_MOVIES_MINUS_1: Int = -1
        private const val NUM_OF_MOVIES_0: Int = 0
    }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    val errorMessage: StateFlow<Int?> = _errorMessage

    private val _messageSnackBar: MutableStateFlow<Int?> = MutableStateFlow(null)
    val messageSnackBar: StateFlow<Int?> = _messageSnackBar

    private val _user: MutableStateFlow<ResultData<UserUiModel>> =  MutableStateFlow(ResultData.Loading)
    val user: StateFlow<ResultData<UserUiModel>> = _user

    val uiState: StateFlow<UserDetailsUiState> = combine(
        isLoading, errorMessage, user, messageSnackBar
    ) { isLoading, errorMessage, user, messageSnackBar ->

        when (user) {

            ResultData.Loading -> {
                UserDetailsUiState(isLoading = true)
            }
            is ResultData.Error -> {
                UserDetailsUiState(
                    errorMessage = (user.failure as UserFailure).message,
                    messageSnackBar = messageSnackBar
                )
            }
            is ResultData.Success<UserUiModel> -> {

                val _user: UserUiModel = user.data

                UserDetailsUiState(
                    user = _user,
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
            initialValue = UserDetailsUiState(isLoading = true)
        )

    fun snackBarMessageShown() {
        _messageSnackBar.value = null
    }

    private fun getUserViaRepository(
        params: Params,
        isRefresh: Boolean = false,
    ) = viewModelScope.launch(context = coroutineDispatcherIo) {

        _user.value = ResultData.Loading

        val userResultData: ResultData<UserModel> =
            userRepository.getUser(params = params, forceUpdate = isRefresh)

        if (userResultData.succeeded) {
            val userEntity = (userResultData as ResultData.Success).data

            val userModel: UserModel = userModelMapper.transform(userEntity)

            val userUiModel: UserUiModel = userUiModelMapper.transform(userModel)

            _user.value = ResultData.Success(data = userUiModel)

        } else {
            val error: ResultData.Error = (userResultData as ResultData.Error)
            _user.value = error
        }

    }

    fun loadUser(params: UserParams, isRefresh: Boolean){
        viewModelScope.launch/*(context = coroutineDispatcherIo)*/ {

            var usersCount by Delegates.notNull<Int>()

            val usersCountDataResultEvent = userRepository.countAllUsers(params = None())

            usersCount = if (usersCountDataResultEvent.succeeded)
                (usersCountDataResultEvent as ResultData.Success).data
            else
                NUM_OF_MOVIES_MINUS_1

            if (usersCount > NUM_OF_MOVIES_0 || isRefresh){

                if (hasInternetConnection(application)){

                    getUserViaRepository(
                        params = params,
                        isRefresh = true,
                    )
                } else {

                    withContext(Dispatchers.Main){
                        Toast.makeText(
                            application,
                            R.string.message_no_internet_searching_cached_users,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    getUserViaRepository(
                        params = params,
                        isRefresh = false
                    )
                }
            } else {

                if (hasInternetConnection(application)){
                    getUserViaRepository(
                        params = params,
                        isRefresh = true
                    )
                } else {

                    _messageSnackBar.value = R.string.message_no_internet_connectivity

                    _user.value = ResultData.Error(
                        failure = UserFailure.UserRemoteError(
                            message = R.string.message_no_internet_connectivity
                        )
                    )
                }
            }

        }
    }

}