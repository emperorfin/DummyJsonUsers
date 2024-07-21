package emperorfin.android.dummyjsonusers.domain.uilayer.event.input

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.UserModelParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.AddressParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.BankParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CompanyParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CryptoParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.HairParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class UserParams(
    override val id: String? = null,
    override val firstName: String? = null,
    override val lastName: String? = null,
    override val maidenName: String? = null,
    override val age: Int? = null,
    override val gender: String? = null,
    override val email: String? = null,
    override val phone: String? = null,
    override val username: String? = null,
    override val password: String? = null,
    override val birthDate: String? = null,
    override val image: String? = null,
    override val bloodGroup: String? = null,
    override val height: Double? = null,
    override val weight: Double? = null,
    override val eyeColor: String? = null,
    override val hair: HairParams? = null,
    override val ip: String? = null,
    override val address: AddressParams? = null,
    override val macAddress: String? = null,
    override val university: String? = null,
    override val bank: BankParams? = null,
    override val company: CompanyParams? = null,
    override val ein: String? = null,
    override val ssn: String? = null,
    override val userAgent: String? = null,
    override val crypto: CryptoParams? = null,
    override val role: String? = null,
    val othersArgs: Map<String, String>? = null
) : UserModelParams
