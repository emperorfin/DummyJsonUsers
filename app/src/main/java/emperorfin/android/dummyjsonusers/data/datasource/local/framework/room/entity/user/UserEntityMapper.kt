package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user

import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.AddressEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.BankEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.CompanyEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.CoordinatesEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.CryptoEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.HairEntity
import emperorfin.android.dummyjsonusers.domain.model.user.UserModel
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


class UserEntityMapper @Inject constructor() {

    fun transform(user: UserModel): UserEntity {

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

        val hair = HairEntity(
            color = user.hair.color,
            type = user.hair.type
        )

        val coordinatesAddress = CoordinatesEntity(
            lat = user.address.coordinates.lat,
            lng = user.address.coordinates.lng,
        )

        val coordinatesCompany = CoordinatesEntity(
            lat = user.company.address.coordinates.lat,
            lng = user.company.address.coordinates.lng,
        )

        val addressHome = AddressEntity(
            address = user.address.address,
            city = user.address.city,
            state = user.address.state,
            stateCode = user.address.stateCode,
            postalCode = user.address.postalCode,
            country = user.address.country,
            coordinates = coordinatesAddress,
        )

        val addressCompany = AddressEntity(
            address = user.company.address.address,
            city = user.company.address.city,
            state = user.company.address.state,
            stateCode = user.company.address.stateCode,
            postalCode = user.company.address.postalCode,
            country = user.company.address.country,
            coordinates = coordinatesCompany,
        )

        val bank = BankEntity(
            cardExpire = user.bank.cardExpire,
            cardNumber = user.bank.cardNumber,
            cardType = user.bank.cardType,
            currency = user.bank.currency,
            iban = user.bank.iban,
        )

        val company = CompanyEntity(
            department = user.company.department,
            name = user.company.name,
            title = user.company.title,
            address = addressCompany,
        )

        val crypto = CryptoEntity(
            coin = user.crypto.coin,
            wallet = user.crypto.wallet,
            network = user.crypto.network,
        )

        return UserEntity.newInstance(
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
        )
        
    }
    
}