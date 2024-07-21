package emperorfin.android.dummyjsonusers.domain.datalayer.repository

import emperorfin.android.dummyjsonusers.domain.model.user.UserModel
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.ResultData
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.Params


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 21st July, 2024.
 */


interface IUserRepository {

    suspend fun countAllUsers(params: Params, countRemotely: Boolean = false): ResultData<Int>

    suspend fun getUsers(params: Params, forceUpdate: Boolean = false): ResultData<List<UserModel>>

    suspend fun getUser(params: Params, forceUpdate: Boolean = false): ResultData<UserModel>

    suspend fun saveUser(user: UserModel, saveRemotely: Boolean = false): ResultData<Long>

    suspend fun deleteUser(params: Params, deleteRemotely: Boolean = false): ResultData<Int>

}