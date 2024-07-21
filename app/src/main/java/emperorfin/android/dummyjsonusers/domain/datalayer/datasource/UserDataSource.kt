package emperorfin.android.dummyjsonusers.domain.datalayer.datasource

import emperorfin.android.dummyjsonusers.domain.model.user.UserModel
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.ResultData
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.Params


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


interface UserDataSource {

    suspend fun countAllUsers(params: Params): ResultData<Int>

    suspend fun getUsers(params: Params): ResultData<List<UserModel>>

    suspend fun getUser(params: Params): ResultData<UserModel>

    suspend fun saveUser(user: UserModel): ResultData<Long>

    suspend fun deleteUser(params: Params): ResultData<Int>

}