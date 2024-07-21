package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class Coordinates(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
) : Serializable
