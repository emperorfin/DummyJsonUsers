package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.api

import emperorfin.android.dummyjsonusers.data.constant.StringConstants.ERROR_MESSAGE_NOT_YET_IMPLEMENTED
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint.UserDetailsResponse
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint.UserSearchResponse
import emperorfin.android.dummyjsonusers.domain.datalayer.dao.IUserDao
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


interface UserApi : IUserDao {

    companion object {
        const val PAGE_LIMIT: Int = IUserDao.REMOTE_PAGE_LIMIT
    }

//    @GET("/users?limit=10&")
    @GET("/users?limit=$PAGE_LIMIT&")
    override suspend fun getRemoteUsers(@Query("skip") skip: String): Response<UserSearchResponse>

    @GET("/users/{id}")
    override suspend fun getRemoteUser(@Path("id") id: String): Response<UserDetailsResponse>

    override suspend fun getUser(id: String): UserEntity {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

    override suspend fun getUsers(name: String): List<UserEntity> {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

    override suspend fun getUsers(): List<UserEntity> {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

    override suspend fun insertUser(user: UserEntity): Long {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

    override suspend fun deleteUser(id: String): Int {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

    override suspend fun countAllUsers(): Int {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

}