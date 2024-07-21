package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class Address(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("stateCode")
    val stateCode: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("coordinates")
    val coordinates: Coordinates,
    @SerializedName("country")
    val country: String,
) : Serializable
