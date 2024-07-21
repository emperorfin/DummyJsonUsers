package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.COLUMN_INFO_ID
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.UserEntity.Companion.TABLE_NAME
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.AddressEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.BankEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.CompanyEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.CryptoEntity
import emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded.HairEntity
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.UserModelParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [COLUMN_INFO_ID]
)
data class UserEntity(
    @ColumnInfo(name = COLUMN_INFO_ID)
    override val id: String,
    @ColumnInfo(name = COLUMN_INFO_FIRST_NAME)
    override val firstName: String,
    @ColumnInfo(name = COLUMN_INFO_LAST_NAME)
    override val lastName: String,
    @ColumnInfo(name = COLUMN_INFO_MAIDEN_NAME)
    override val maidenName: String,
    @ColumnInfo(name = COLUMN_INFO_AGE)
    override val age: Int,
    @ColumnInfo(name = COLUMN_INFO_GENDER)
    override val gender: String,
    @ColumnInfo(name = COLUMN_INFO_EMAIL)
    override val email: String,
    @ColumnInfo(name = COLUMN_INFO_PHONE)
    override val phone: String,
    @ColumnInfo(name = COLUMN_INFO_USERNAME)
    override val username: String,
    @ColumnInfo(name = COLUMN_INFO_PASSWORD)
    override val password: String,
    @ColumnInfo(name = COLUMN_INFO_BIRTH_DATE)
    override val birthDate: String,
    @ColumnInfo(name = COLUMN_INFO_IMAGE)
    override val image: String,
    @ColumnInfo(name = COLUMN_INFO_BLOODGROUP)
    override val bloodGroup: String,
    @ColumnInfo(name = COLUMN_INFO_HEIGHT)
    override val height: Double,
    @ColumnInfo(name = COLUMN_INFO_WEIGHT)
    override val weight: Double,
    @ColumnInfo(name = COLUMN_INFO_EYE_COLOR)
    override val eyeColor: String,
    @ColumnInfo(name = COLUMN_INFO_HAIR)
    override val hair: HairEntity,
    @ColumnInfo(name = COLUMN_INFO_IP)
    override val ip: String,
    @ColumnInfo(name = COLUMN_INFO_ADDRESS)
    override val address: AddressEntity,
    @ColumnInfo(name = COLUMN_INFO_MAC_ADDRESS)
    override val macAddress: String,
    @ColumnInfo(name = COLUMN_INFO_UNIVERSITY)
    override val university: String,
    @ColumnInfo(name = COLUMN_INFO_BANK)
    override val bank: BankEntity,
    @ColumnInfo(name = COLUMN_INFO_COMPANY)
    override val company: CompanyEntity,
    @ColumnInfo(name = COLUMN_INFO_EIN)
    override val ein: String,
    @ColumnInfo(name = COLUMN_INFO_SSN)
    override val ssn: String,
    @ColumnInfo(name = COLUMN_INFO_USER_AGENT)
    override val userAgent: String,
    @ColumnInfo(name = COLUMN_INFO_CRYPTO)
    override val crypto: CryptoEntity,
    @ColumnInfo(name = COLUMN_INFO_ROLE)
    override val role: String
) : UserModelParams {

    companion object {

        const val TABLE_NAME = "table_users"

        const val COLUMN_INFO_ID = "id"
        const val COLUMN_INFO_FIRST_NAME = "first_name"
        const val COLUMN_INFO_LAST_NAME = "last_name"
        const val COLUMN_INFO_MAIDEN_NAME = "maiden_name"
        const val COLUMN_INFO_AGE = "age"
        const val COLUMN_INFO_GENDER = "gender"
        const val COLUMN_INFO_EMAIL = "email"
        const val COLUMN_INFO_PHONE = "phone"
        const val COLUMN_INFO_USERNAME = "username"
        const val COLUMN_INFO_PASSWORD = "password"
        const val COLUMN_INFO_BIRTH_DATE = "birth_date"
        const val COLUMN_INFO_IMAGE = "image"
        const val COLUMN_INFO_BLOODGROUP = "bloodgroup"
        const val COLUMN_INFO_HEIGHT = "height"
        const val COLUMN_INFO_WEIGHT = "weight"
        const val COLUMN_INFO_EYE_COLOR = "eye_color"
        const val COLUMN_INFO_HAIR = "hair"
        const val COLUMN_INFO_IP = "ip"
        const val COLUMN_INFO_ADDRESS = "address"
        const val COLUMN_INFO_MAC_ADDRESS = "macAddress"
        const val COLUMN_INFO_UNIVERSITY = "university"
        const val COLUMN_INFO_BANK = "bank"
        const val COLUMN_INFO_COMPANY = "company"
        const val COLUMN_INFO_EIN = "ein"
        const val COLUMN_INFO_SSN = "ssn"
        const val COLUMN_INFO_USER_AGENT = "user_agent"
        const val COLUMN_INFO_CRYPTO = "crypto"
        const val COLUMN_INFO_ROLE = "role"

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
            hair: HairEntity,
            ip: String,
            address: AddressEntity,
            macAddress: String,
            university: String,
            bank: BankEntity,
            company: CompanyEntity,
            ein: String,
            ssn: String,
            userAgent: String,
            crypto: CryptoEntity,
            role: String
        ): UserEntity {
            return UserEntity(
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
