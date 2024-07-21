package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user

import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.AddressDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.BankDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.CompanyDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.CryptoDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.HairDataTransferObject
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.UserModelParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class UserDataTransferObject private constructor(
    override val id: String,
    override val firstName: String,
    override val lastName: String,
    override val maidenName: String,
    override val age: Int,
    override val gender: String,
    override val email: String,
    override val phone: String,
    override val username: String,
    override val password: String,
    override val birthDate: String,
    override val image: String,
    override val bloodGroup: String,
    override val height: Double,
    override val weight: Double,
    override val eyeColor: String,
    override val hair: HairDataTransferObject,
    override val ip: String,
    override val address: AddressDataTransferObject,
    override val macAddress: String,
    override val university: String,
    override val bank: BankDataTransferObject,
    override val company: CompanyDataTransferObject,
    override val ein: String,
    override val ssn: String,
    override val userAgent: String,
    override val crypto: CryptoDataTransferObject,
    override val role: String
) : UserModelParams {

    companion object {

        fun newInstance(
            id: String,
            firstName: String,
            lastName: String,
            maidenName: String,
            age: Int,
            gender: String,
            email: String,
            phone: String,
            username: String,
            password: String,
            birthDate: String,
            image: String,
            bloodGroup: String,
            height: Double,
            weight: Double,
            eyeColor: String,
            hair: HairDataTransferObject,
            ip: String,
            address: AddressDataTransferObject,
            macAddress: String,
            university: String,
            bank: BankDataTransferObject,
            company: CompanyDataTransferObject,
            ein: String,
            ssn: String,
            userAgent: String,
            crypto: CryptoDataTransferObject,
            role: String
        ): UserDataTransferObject {
            return UserDataTransferObject(
                id = id,
                firstName = firstName,
                lastName = lastName,
                maidenName = maidenName,
                age = age,
                gender = gender,
                email = email,
                phone = phone,
                username = username,
                password = password,
                birthDate = birthDate,
                image = image,
                bloodGroup = bloodGroup,
                height = height,
                weight = weight,
                eyeColor = eyeColor,
                hair = hair,
                ip = ip,
                address = address,
                macAddress = macAddress,
                university = university,
                bank = bank,
                company = company,
                ein = ein,
                ssn = ssn,
                userAgent = userAgent,
                crypto = crypto,
                role = role,
            )
        }

    }

}
