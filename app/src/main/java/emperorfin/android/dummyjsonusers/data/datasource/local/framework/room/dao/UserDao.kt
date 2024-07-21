package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import emperorfin.android.dummyjsonusers.data.constant.StringConstants.ERROR_MESSAGE_NOT_YET_IMPLEMENTED
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_FIRST_NAME
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_ID
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_LAST_NAME
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.TABLE_NAME
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint.UserDetailsResponse
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint.UserSearchResponse
import emperorfin.android.dummyjsonusers.domain.datalayer.dao.IUserDao
import retrofit2.Response


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


@Dao
interface UserDao : IUserDao {

    @Query("SELECT COUNT(*) FROM $TABLE_NAME")
    override suspend fun countAllUsers(): Int

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_INFO_FIRST_NAME ASC")
    override suspend fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_INFO_FIRST_NAME LIKE '%' || :name || '%' OR $COLUMN_INFO_LAST_NAME LIKE '%' || :name || '%' ORDER BY $COLUMN_INFO_FIRST_NAME ASC")
    override suspend fun getUsers(name: String): List<UserEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_INFO_ID = :id")
    override suspend fun getUser(id: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertUser(user: UserEntity): Long

    @Query("DELETE FROM $TABLE_NAME WHERE $COLUMN_INFO_ID = :id")
    override suspend fun deleteUser(id: String): Int

    override suspend fun getRemoteUser(id: String): Response<UserDetailsResponse> {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

    override suspend fun getRemoteUsers(skip: String): Response<UserSearchResponse> {
        throw IllegalStateException(
            ERROR_MESSAGE_NOT_YET_IMPLEMENTED
        )
    }

}