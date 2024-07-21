package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint

import com.google.gson.annotations.SerializedName
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user.User
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class UserSearchResponse(
    @SerializedName("users")
    val users: List<User>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int,
) : Serializable
