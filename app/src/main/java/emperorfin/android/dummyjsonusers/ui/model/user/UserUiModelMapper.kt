package emperorfin.android.dummyjsonusers.ui.model.user

import emperorfin.android.dummyjsonusers.domain.model.user.UserModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.AddressUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.BankUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.CompanyUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.CoordinatesUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.CryptoUiModel
import emperorfin.android.dummyjsonusers.ui.model.user.embedded.HairUiModel
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


class UserUiModelMapper @Inject constructor() {

    fun transform(user: UserModel): UserUiModel {

        val id: String = user.id
        val firstName: String = user.firstName
        val lastName: String = user.lastName
        val maidenName: String = user.maidenName
        val age: Int = user.age
        val gender: String = user.gender
        val email: String = user.email
        val phone: String = user.phone
        val username: String = user.username
        val password: String = user.password
        val birthDate: String = user.birthDate
        val image: String = user.image
        val bloodGroup: String = user.bloodGroup
        val height: Double = user.height
        val weight: Double = user.weight
        val eyeColor: String = user.eyeColor
        val ip: String = user.ip
        val macAddress: String = user.macAddress
        val university: String = user.university
        val ein: String = user.ein
        val ssn: String = user.ssn
        val userAgent: String = user.userAgent
        val role: String = user.role

        val hair = HairUiModel(
            color = user.hair.color,
            type = user.hair.type
        )

        val coordinatesAddress = CoordinatesUiModel(
            lat = user.address.coordinates.lat,
            lng = user.address.coordinates.lng,
        )

        val coordinatesCompany = CoordinatesUiModel(
            lat = user.company.address.coordinates.lat,
            lng = user.company.address.coordinates.lng,
        )

        val addressHome = AddressUiModel(
            address = user.address.address,
            city = user.address.city,
            state = user.address.state,
            stateCode = user.address.stateCode,
            postalCode = user.address.postalCode,
            country = user.address.country,
            coordinates = coordinatesAddress,
        )

        val addressCompany = AddressUiModel(
            address = user.company.address.address,
            city = user.company.address.city,
            state = user.company.address.state,
            stateCode = user.company.address.stateCode,
            postalCode = user.company.address.postalCode,
            country = user.company.address.country,
            coordinates = coordinatesCompany,
        )

        val bank = BankUiModel(
            cardExpire = user.bank.cardExpire,
            cardNumber = user.bank.cardNumber,
            cardType = user.bank.cardType,
            currency = user.bank.currency,
            iban = user.bank.iban,
        )

        val company = CompanyUiModel(
            department = user.company.department,
            name = user.company.name,
            title = user.company.title,
            address = addressCompany,
        )

        val crypto = CryptoUiModel(
            coin = user.crypto.coin,
            wallet = user.crypto.wallet,
            network = user.crypto.network,
        )

        val heOrShe = if (gender == "male") "He" else "She"
        val address = user.address.address
        val city = user.address.city
        val state = user.address.state
        val country = user.address.country

        val companyJobTitle = user.company.title
        val companyDept = user.company.department
        val companyName = user.company.name
        val companyAddress = user.company.address.address
        val companyCity = user.company.address.city
        val companyState = user.company.address.state
        val companyCountry = user.company.address.country

        val aboutUser = "$firstName is from $address, $city, $state, $country. " +
                "$heOrShe studied at $university. $heOrShe works as a" +
                " $companyJobTitle under the department of $companyDept at " +
                "$companyName which is located at $companyAddress, $companyCity, $companyState, $companyCountry."

        val keywordAge = "age: $age"
        val keywordBloodGroup = "bloodgroup: $bloodGroup"
        val keywordHeight = "height: $height"
        val keywordWeight = "weight: $weight"
        val keywordEyeColor = "${eyeColor.lowercase()} eye"
        val keywordHair = "${hair.type.lowercase()} ${hair.color.lowercase()} hair"

        val keywords = listOf(
            gender,
            keywordAge,
            keywordBloodGroup,
            keywordHeight,
            keywordWeight,
            keywordEyeColor,
            keywordHair
        )

        return UserUiModel.newInstance(
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
            address = addressHome,
            macAddress = macAddress,
            university = university,
            bank = bank,
            company = company,
            ein = ein,
            ssn = ssn,
            userAgent = userAgent,
            crypto = crypto,
            role = role,
            about = aboutUser,
            keywords = keywords
        )

    }

}