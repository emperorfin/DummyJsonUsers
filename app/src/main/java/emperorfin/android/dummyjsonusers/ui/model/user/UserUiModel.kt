package emperorfin.android.dummyjsonusers.ui.model.user

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.UserUiModelParams
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.AddressUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.BankUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.CompanyUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.CryptoUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.HairUiModel


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class UserUiModel private constructor(
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
    override val hair: HairUiModel,
    override val ip: String,
    override val address: AddressUiModel,
    override val macAddress: String,
    override val university: String,
    override val bank: BankUiModel,
    override val company: CompanyUiModel,
    override val ein: String,
    override val ssn: String,
    override val userAgent: String,
    override val crypto: CryptoUiModel,
    override val role: String,
    override val about: String?,
    override val keywords: List<String>?
) : UserUiModelParams {

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
            hair: HairUiModel,
            ip: String,
            address: AddressUiModel,
            macAddress: String,
            university: String,
            bank: BankUiModel,
            company: CompanyUiModel,
            ein: String,
            ssn: String,
            userAgent: String,
            crypto: CryptoUiModel,
            role: String,
            about: String,
            keywords: List<String>
        ): UserUiModel {
            return UserUiModel(
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
                about = about,
                keywords = keywords,
            )
        }

    }

}
