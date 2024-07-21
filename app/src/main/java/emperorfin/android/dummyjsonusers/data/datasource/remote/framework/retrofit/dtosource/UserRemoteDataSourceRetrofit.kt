package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dtosource

import emperorfin.android.dummyjsonusers.data.constant.StringConstants.ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED
import emperorfin.android.dummyjsonusers.data.constant.StringConstants.ERROR_MESSAGE_NOT_YET_IMPLEMENTED
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.UserDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.UserDataTransferObjectMapper
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.AddressDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.BankDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.CompanyDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.CoordinatesDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.CryptoDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded.HairDataTransferObject
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint.UserDetailsResponse
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.endpoint.UserSearchResponse
import emperorfin.android.dummyjsonusers.di.IoDispatcher
import emperorfin.android.dummyjsonusers.di.MainDispatcher
import emperorfin.android.dummyjsonusers.di.RemoteUserDao
import emperorfin.android.dummyjsonusers.domain.datalayer.dao.IUserDao
import emperorfin.android.dummyjsonusers.domain.datalayer.datasource.UserDataSource
import emperorfin.android.dummyjsonusers.domain.exception.UserFailure
import emperorfin.android.dummyjsonusers.domain.model.user.UserModel
import emperorfin.android.dummyjsonusers.domain.model.user.UserModelMapper
import emperorfin.android.dummyjsonusers.domain.uilayer.event.input.None
import emperorfin.android.dummyjsonusers.domain.uilayer.event.input.UserParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.ResultData
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.Params
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 21st July, 2024.
 */


data class UserRemoteDataSourceRetrofit @Inject internal constructor(
    @RemoteUserDao private val userDao: IUserDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val userDtoMapper: UserDataTransferObjectMapper,
    private val userModelMapper: UserModelMapper
) : UserDataSource {
    override suspend fun countAllUsers(params: Params): ResultData<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getUsers(params: Params): ResultData<List<UserModel>> = withContext(ioDispatcher) {
        when(params){
            is None -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }
            is UserParams -> {

                return@withContext try {

                    val skip: String = params.othersArgs!!.getValue("skip")

                    val response = userDao.getRemoteUsers(
                        skip = skip,
                    )

                    withContext(mainDispatcher){
                        if (response.isSuccessful){

                            response.body()?.let {

                                val usersModel: List<UserModel> =
                                    buildUserModelList(response = it)

                                // try block doesn't seem to return without return@withContext
                                return@withContext ResultData.Success(usersModel)
                            }
                        }

//                        println("HTTP status code: ${response.code()}")

                        return@withContext ResultData.Error(failure = UserFailure.GetUserRemoteError())
                    }

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserRemoteError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun getUser(params: Params): ResultData<UserModel> = withContext(ioDispatcher) {
        when(params){
            is None -> {
                throw IllegalArgumentException(ERROR_MESSAGE_INAPPROPRIATE_ARGUMENT_PASSED)
            }
            is UserParams -> {

                return@withContext try {

                    val response = userDao.getRemoteUser(
                        id = params.id!!,
                    )

                    withContext(mainDispatcher){
                        if (response.isSuccessful){

                            response.body()?.let {

                                val movieModel: UserModel = buildUserModel(response = it)

                                // try block doesn't seem to return without return@withContext
                                return@withContext ResultData.Success(movieModel)
                            }
                        }

//                        println("HTTP status code: ${response.code()}")

                        return@withContext ResultData.Error(failure = UserFailure.GetUserRemoteError())
                    }

                } catch (e: Exception){
                    return@withContext ResultData.Error(failure = UserFailure.UserRemoteError(cause = e))
                }
            }
            else -> throw NotImplementedError(ERROR_MESSAGE_NOT_YET_IMPLEMENTED)
        }
    }

    override suspend fun saveUser(user: UserModel): ResultData<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(params: Params): ResultData<Int> {
        TODO("Not yet implemented")
    }

    private fun buildUserModelList(response: UserSearchResponse): List<UserModel> {

        val responseUser = response.users

        val usersDto = mutableListOf<UserDataTransferObject>()

        responseUser.forEach { user ->

            val id: String = user.id.toString()
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

            val hair = HairDataTransferObject(
                color = user.hair.color,
                type = user.hair.type
            )

            val coordinatesAddress = CoordinatesDataTransferObject(
                lat = user.address.coordinates.lat,
                lng = user.address.coordinates.lng,
            )

            val coordinatesCompany = CoordinatesDataTransferObject(
                lat = user.company.address.coordinates.lat,
                lng = user.company.address.coordinates.lng,
            )

            val addressHome = AddressDataTransferObject(
                address = user.address.address,
                city = user.address.city,
                state = user.address.state,
                stateCode = user.address.stateCode,
                postalCode = user.address.postalCode,
                country = user.address.country,
                coordinates = coordinatesAddress,
            )

            val addressCompany = AddressDataTransferObject(
                address = user.company.address.address,
                city = user.company.address.city,
                state = user.company.address.state,
                stateCode = user.company.address.stateCode,
                postalCode = user.company.address.postalCode,
                country = user.company.address.country,
                coordinates = coordinatesCompany,
            )

            val bank = BankDataTransferObject(
                cardExpire = user.bank.cardExpire,
                cardNumber = user.bank.cardNumber,
                cardType = user.bank.cardType,
                currency = user.bank.currency,
                iban = user.bank.iban,
            )

            val company = CompanyDataTransferObject(
                department = user.company.department,
                name = user.company.name,
                title = user.company.title,
                address = addressCompany,
            )

            val crypto = CryptoDataTransferObject(
                coin = user.crypto.coin,
                wallet = user.crypto.wallet,
                network = user.crypto.network,
            )

            val userDto = UserDataTransferObject.newInstance(
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

            usersDto.add(userDto)

        }

        return usersDto.map {
            userModelMapper.transform(it)
        }

    }

    private fun buildUserModel(response: UserDetailsResponse): UserModel {

        val id: String = response.id.toString()
        val firstName: String = response.firstName
        val lastName: String = response.lastName
        val maidenName: String = response.maidenName
        val age: Int = response.age
        val gender: String = response.gender
        val email: String = response.email
        val phone: String = response.phone
        val username: String = response.username
        val password: String = response.password
        val birthDate: String = response.birthDate
        val image: String = response.image
        val bloodGroup: String = response.bloodGroup
        val height: Double = response.height
        val weight: Double = response.weight
        val eyeColor: String = response.eyeColor
        val ip: String = response.ip
        val macAddress: String = response.macAddress
        val university: String = response.university
        val ein: String = response.ein
        val ssn: String = response.ssn
        val userAgent: String = response.userAgent
        val role: String = response.role

        val hair = HairDataTransferObject(
            color = response.hair.color,
            type = response.hair.type
        )

        val coordinatesAddress = CoordinatesDataTransferObject(
            lat = response.address.coordinates.lat,
            lng = response.address.coordinates.lng,
        )

        val coordinatesCompany = CoordinatesDataTransferObject(
            lat = response.company.address.coordinates.lat,
            lng = response.company.address.coordinates.lng,
        )

        val addressHome = AddressDataTransferObject(
            address = response.address.address,
            city = response.address.city,
            state = response.address.state,
            stateCode = response.address.stateCode,
            postalCode = response.address.postalCode,
            country = response.address.country,
            coordinates = coordinatesAddress,
        )

        val addressCompany = AddressDataTransferObject(
            address = response.company.address.address,
            city = response.company.address.city,
            state = response.company.address.state,
            stateCode = response.company.address.stateCode,
            postalCode = response.company.address.postalCode,
            country = response.company.address.country,
            coordinates = coordinatesCompany,
        )

        val bank = BankDataTransferObject(
            cardExpire = response.bank.cardExpire,
            cardNumber = response.bank.cardNumber,
            cardType = response.bank.cardType,
            currency = response.bank.currency,
            iban = response.bank.iban,
        )

        val company = CompanyDataTransferObject(
            department = response.company.department,
            name = response.company.name,
            title = response.company.title,
            address = addressCompany,
        )

        val crypto = CryptoDataTransferObject(
            coin = response.crypto.coin,
            wallet = response.crypto.wallet,
            network = response.crypto.network,
        )

        val userDto = UserDataTransferObject.newInstance(
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

        return userModelMapper.transform(userDto)

    }
}
