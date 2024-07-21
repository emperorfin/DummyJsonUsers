package emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.AddressParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.BankParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CompanyParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CryptoParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.HairParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


interface UserDataTransferObjectParams : Params {
    val id: String?
    val firstName: String?
    val lastName: String?
    val maidenName: String?
    val age: Int?
    val gender: String?
    val email: String?
    val phone: String?
    val username: String?
    val password: String?
    val birthDate: String?
    val image: String?
    val bloodGroup: String?
    val height: Double?
    val weight: Double?
    val eyeColor: String?
    val hair: HairParams?
    val ip: String?
    val address: AddressParams?
    val macAddress: String?
    val university: String?
    val bank: BankParams?
    val company: CompanyParams?
    val ein: String?
    val ssn: String?
    val userAgent: String?
    val crypto: CryptoParams?
    val role: String?
}