package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entitysource

import emperorfin.android.dummyjsonusers.R
import emperorfin.android.dummyjsonusers.data.constant.StringConstants.ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED
import emperorfin.android.dummyjsonusers.data.constant.StringConstants.ERROR_MESSAGE_NOT_YET_IMPLEMENTED
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntityMapper
import emperorfin.android.dummyjsonusers.di.IoDispatcher
import emperorfin.android.dummyjsonusers.di.LocalUserDao
import emperorfin.android.dummyjsonusers.domain.datalayer.dao.IUserDao
import emperorfin.android.dummyjsonusers.domain.datalayer.datasource.UserDataSource
import emperorfin.android.dummyjsonusers.domain.exception.UserFailure
import emperorfin.android.dummyjsonusers.domain.model.user.UserModel
import emperorfin.android.dummyjsonusers.domain.model.user.UserModelMapper
import emperorfin.android.dummyjsonusers.domain.uilayer.event.input.None
import emperorfin.android.dummyjsonusers.domain.uilayer.event.input.UserParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.ResultData
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.Params
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class UserLocalDataSourceRoom @Inject internal constructor(
    @LocalUserDao private val userDao: IUserDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val userEntityMapper: UserEntityMapper,
    private val userModelMapper: UserModelMapper
) : UserDataSource {

    private companion object {
        const val NUM_OF_USERS_0: Int = 0
        const val NUM_OF_ROWS_DELETED_1: Int = 1

        const val TABLE_ROW_ID_1: Long = 1L
    }

    override suspend fun countAllUsers(params: Params): ResultData<Int> = withContext(ioDispatcher) {
        when(params){
            is None -> {
                return@withContext try {

                    val numOfUsers: Int = userDao.countAllUsers()

                    if (numOfUsers > NUM_OF_USERS_0) {
                        return@withContext ResultData.Success(data = numOfUsers)
                    } else if (numOfUsers == NUM_OF_USERS_0) {
                        return@withContext ResultData.Error(failure = UserFailure.NonExistentUserDataLocalError())
                    }

                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError())

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError(cause = e))
                }
            }
            is UserParams -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }


    }

    override suspend fun getUsers(params: Params): ResultData<List<UserModel>> = withContext(ioDispatcher) {

        when(params){
            is None -> {
                return@withContext try {
                    val entityUsers: List<UserEntity> = userDao.getUsers() as List<UserEntity>

                    if (entityUsers == null) // Deliberate check but shouldn't do this
                        return@withContext ResultData.Error(failure = UserFailure.UserLocalError())
                    else if (entityUsers.isEmpty())
                        return@withContext ResultData.Error(failure = UserFailure.UserListNotAvailableLocalError())

                    val modelUsers = entityUsers.map {
                        userModelMapper.transform(it)
                    }

                    return@withContext ResultData.Success(modelUsers)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError(cause = e))
                }
            }
            is UserParams -> {
                return@withContext try {

                    val entityUsers: List<UserEntity> = userDao.getUsers(params.firstName!!) as List<UserEntity>

                    if (entityUsers == null) // Deliberate check but shouldn't do this
                        return@withContext ResultData.Error(failure = UserFailure.UserLocalError())
                    else if (entityUsers.isEmpty())
                        return@withContext ResultData.Error(failure = UserFailure.UserListNotAvailableLocalError())

                    val modelUsers = entityUsers.map {
                        userModelMapper.transform(it)
                    }

                    return@withContext ResultData.Success(modelUsers)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun getUser(params: Params): ResultData<UserModel> = withContext(ioDispatcher) {

        when(params){
            is None -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }
            is UserParams -> {
                return@withContext try {

                    val entityUser: UserEntity = userDao.getUser(params.id!!) as UserEntity

                    if (entityUser == null) // Deliberate check but shouldn't do this
                        return@withContext ResultData.Error(failure = UserFailure.UserLocalError())

                    val modelUser = userModelMapper.transform(entityUser)

                    return@withContext ResultData.Success(modelUser)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserLocalError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun saveUser(user: UserModel): ResultData<Long> = withContext(ioDispatcher){

        val entityUser = userEntityMapper.transform(user)

        // TODO: Consider putting this in a try/catch block and then write test cases for it.
        val tableRowId: Long = userDao.insertUser(entityUser)

        if (tableRowId < TABLE_ROW_ID_1)
            return@withContext ResultData.Error(
                UserFailure.InsertUserLocalError(message = R.string.error_local_insert_user)
            )

        return@withContext ResultData.Success(tableRowId)
    }

    override suspend fun deleteUser(params: Params): ResultData<Int> = withContext(ioDispatcher) {
        when(params){
            is None -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }

            is UserParams -> {
                return@withContext try {

                    val numOfUserDeleted: Int = userDao.deleteUser(params.id!!)

                    if (numOfUserDeleted < NUM_OF_ROWS_DELETED_1) {
                        return@withContext ResultData.Error(failure = UserFailure.DeleteUserLocalError(R.string.error_local_delete_user))
                    }

                    return@withContext ResultData.Success(numOfUserDeleted)

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.DeleteUserLocalError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

}
