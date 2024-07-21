package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class Crypto(
    @SerializedName("coin")
    val coin: String,
    @SerializedName("wallet")
    val wallet: String,
    @SerializedName("network")
    val network: String,
) : Serializable
