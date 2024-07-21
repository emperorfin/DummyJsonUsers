package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class Company(
    @SerializedName("department")
    val department: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("address")
    val address: Address,
) : Serializable
