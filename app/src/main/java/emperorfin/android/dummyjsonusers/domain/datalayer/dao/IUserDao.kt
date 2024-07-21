package emperorfin.android.dummyjsonusers.domain.datalayer.dao

import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint.UserDetailsResponse
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint.UserSearchResponse
import retrofit2.Response


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


interface IUserDao {

    suspend fun countAllUsers(): Int

    suspend fun getUsers(): List<UserEntity>

    suspend fun getUsers(name: String): List<UserEntity>

    suspend fun getRemoteUsers(skip: String): Response<UserSearchResponse>

    suspend fun getUser(id: String): UserEntity

    suspend fun getRemoteUser(id: String): Response<UserDetailsResponse>

    suspend fun insertUser(user: UserEntity): Long

    suspend fun deleteUser(id: String): Int

}