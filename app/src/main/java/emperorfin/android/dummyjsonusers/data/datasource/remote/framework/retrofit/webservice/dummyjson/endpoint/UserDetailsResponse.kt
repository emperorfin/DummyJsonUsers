package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint

import com.google.gson.annotations.SerializedName
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user.Address
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user.Bank
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user.Company
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user.Crypto
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.jsonobject.user.Hair
import java.io.Serializable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class UserDetailsResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("maidenName")
    val maidenName: String,
    @SerializedName("age")
    val age: Int,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("birthDate")
    val birthDate: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("bloodGroup")
    val bloodGroup: String,
    @SerializedName("height")
    val height: Double,
    @SerializedName("weight")
    val weight: Double,
    @SerializedName("eyeColor")
    val eyeColor: String,
    @SerializedName("hair")
    val hair: Hair,
    @SerializedName("ip")
    val ip: String,
    @SerializedName("address")
    val address: Address,
    @SerializedName("macAddress")
    val macAddress: String,
    @SerializedName("university")
    val university: String,
    @SerializedName("bank")
    val bank: Bank,
    @SerializedName("company")
    val company: Company,
    @SerializedName("ein")
    val ein: String,
    @SerializedName("ssn")
    val ssn: String,
    @SerializedName("userAgent")
    val userAgent: String,
    @SerializedName("crypto")
    val crypto: Crypto,
    @SerializedName("role")
    val role: String,
) : Serializable
