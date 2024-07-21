package emperorfin.android.dummyjsonusers.data.repository

import emperorfin.android.dummyjsonusers.R
import emperorfin.android.dummyjsonusers.di.IoDispatcher
import emperorfin.android.dummyjsonusers.di.UserLocalDataSource
import emperorfin.android.dummyjsonusers.di.UserRemoteDataSource
import emperorfin.android.dummyjsonusers.domain.datalayer.datasource.UserDataSource
import emperorfin.android.dummyjsonusers.domain.datalayer.repository.IUserRepository
import emperorfin.android.dummyjsonusers.domain.exception.UserFailure
import emperorfin.android.dummyjsonusers.domain.model.user.UserModel
import emperorfin.android.dummyjsonusers.domain.model.user.embedded.AddressModel
import emperorfin.android.dummyjsonusers.domain.model.user.embedded.BankModel
import emperorfin.android.dummyjsonusers.domain.model.user.embedded.CompanyModel
import emperorfin.android.dummyjsonusers.domain.model.user.embedded.CoordinatesModel
import emperorfin.android.dummyjsonusers.domain.model.user.embedded.CryptoModel
import emperorfin.android.dummyjsonusers.domain.model.user.embedded.HairModel
import emperorfin.android.dummyjsonusers.domain.uilayer.event.input.UserParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.ResultData
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.Params
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import javax.inject.Inject


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 21st July, 2024.
 */


data class UserRepository @Inject constructor(
    @UserLocalDataSource private val userLocalDataSource: UserDataSource,
    @UserRemoteDataSource private val userRemoteDataSource: UserDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : IUserRepository {

    private var cachedUsers: ConcurrentMap<String, UserModel>? = null

    override suspend fun countAllUsers(params: Params, countRemotely: Boolean): ResultData<Int> = withContext(ioDispatcher) {
        if (countRemotely) {
            return@withContext userRemoteDataSource.countAllUsers(params = params)
        } else {
            return@withContext userLocalDataSource.countAllUsers(params = params)
        }
    }

    override suspend fun getUsers(params: Params, forceUpdate: Boolean): ResultData<List<UserModel>> {
        return withContext(ioDispatcher) {
            // Respond immediately with cache if available and not dirty
            if (!forceUpdate) {
                cachedUsers?.let { users ->
                    return@withContext ResultData.Success(users.values.sortedBy { it.firstName })
                }
            }

            val newUsers: ResultData<List<UserModel>> =
                fetchUsersFromRemoteOrLocal(params = params, forceUpdate = forceUpdate)

            // Refresh the cache with the new users
            (newUsers as? ResultData.Success)?.let { refreshCache(it.data) }

            cachedUsers?.values?.let { users ->
                return@withContext ResultData.Success(users.sortedBy { it.firstName })
            }

            (newUsers as? ResultData.Success)?.let {
                if (it.data.isNotEmpty()) { // it.data.isEmpty()
                    return@withContext ResultData.Success(it.data)
                }
            }

            return@withContext newUsers as ResultData.Error
        }
    }

    override suspend fun getUser(params: Params, forceUpdate: Boolean): ResultData<UserModel> {
        return withContext(ioDispatcher) {
            // Respond immediately with cache if available and not dirty
            if (!forceUpdate) {

                if (params is UserParams) {
                    if (cachedUsers?.containsKey(params.id) == true) {

                        val userCached = cachedUsers?.get(params.id)

                        return@withContext ResultData.Success(userCached!!)
                    }
                }
            }

            val newUser: ResultData<UserModel> =
                fetchUserFromRemoteOrLocal(params = params, forceUpdate = forceUpdate)

            // Refresh the cache with the new user
            (newUser as? ResultData.Success)?.let { refreshCache(it.data) }

            if (params is UserParams) {
                if (cachedUsers?.containsKey(params.id) == true) {

                    val userCached = cachedUsers?.get(params.id)

                    return@withContext ResultData.Success(userCached!!)
                }
            }

            (newUser as? ResultData.Success)?.let {
                return@withContext ResultData.Success(it.data)
            }

            return@withContext newUser as ResultData.Error
        }
    }

    override suspend fun saveUser(user: UserModel, saveRemotely: Boolean): ResultData<Long> = withContext(ioDispatcher) {

        if (saveRemotely) {
            return@withContext userRemoteDataSource.saveUser(user = user)
        } else {
            return@withContext userLocalDataSource.saveUser(user = user)
        }

    }

    override suspend fun deleteUser(params: Params, deleteRemotely: Boolean): ResultData<Int> = withContext(ioDispatcher) {

        if (deleteRemotely) {
            return@withContext userRemoteDataSource.deleteUser(params = params)
        } else {
            return@withContext userLocalDataSource.deleteUser(params = params)
        }
    }

    private suspend fun fetchUsersFromRemoteOrLocal(params: Params, forceUpdate: Boolean): ResultData<List<UserModel>> {
        var isRemoteException = false

        // Remote first
        if (forceUpdate) {
            when (val usersRemote = userRemoteDataSource.getUsers(params = params)) {
//             is Error -> return remoteUsers // Timber.w("Remote data source fetch failed")
                is ResultData.Error -> {
                    if (usersRemote.failure is UserFailure.UserRemoteError)
                        isRemoteException = true
                }
                is ResultData.Success -> {
                    refreshLocalDataSource(users = usersRemote.data)

                    return usersRemote
                }
//             else -> throw IllegalStateException()
                else -> {}
            }
        }

        // Don't read from local if it's forced
        if (forceUpdate) {
            if (isRemoteException)
                return ResultData.Error(
                    UserFailure.GetUserRepositoryError(
                        message = R.string.exception_occurred_remote
                    )
                )

            return ResultData.Error(
                // TODO: Change GetUserRemoteError to GetUserRepositoryError and update
                //  test cases too.
                UserFailure.GetUserRepositoryError(
                    message = R.string.error_cant_force_refresh_users_remote_data_source_unavailable
                )
            )
        }

        // Local if remote fails
        val usersLocal = userLocalDataSource.getUsers(params = params)

        if (usersLocal is ResultData.Success) return usersLocal

        if ((usersLocal as ResultData.Error).failure is UserFailure.UserLocalError)
            return ResultData.Error(
                UserFailure.GetUserRepositoryError(
                    R.string.exception_occurred_local
                )
            )

//        return Error((usersLocal as Error).failure)
        return ResultData.Error(
            UserFailure.GetUserRepositoryError(
                R.string.error_fetching_from_remote_and_local
            )
        )
    }

    private suspend fun fetchUserFromRemoteOrLocal(params: Params, forceUpdate: Boolean): ResultData<UserModel> {
        var isRemoteException = false

        // Remote first
        if (forceUpdate) {
            when (val userRemote = userRemoteDataSource.getUser(params = params)) {
//             is Error -> return remoteUser // Timber.w("Remote data source fetch failed")
                is ResultData.Error -> {
                    if (userRemote.failure is UserFailure.UserRemoteError)
                        isRemoteException = true
                }
                is ResultData.Success -> {
                    refreshLocalDataSource(user = userRemote.data)

                    return userRemote
                }
//             else -> throw IllegalStateException()
                else -> {}
            }
        }

        // Don't read from local if it's forced
        if (forceUpdate) {
            if (isRemoteException)
                return ResultData.Error(
                    UserFailure.GetUserRepositoryError(
                        message = R.string.exception_occurred_remote
                    )
                )

            return ResultData.Error(
                // TODO: Change GetUserRemoteError to GetUserRepositoryError and update
                //  test cases too.
                UserFailure.GetUserRemoteError(
                    message = R.string.error_cant_force_refresh_users_remote_data_source_unavailable
                )
            )
        }

        // Local if remote fails
        val userLocal = userLocalDataSource.getUser(params = params)

        if (userLocal is ResultData.Success) return userLocal

        if ((userLocal as ResultData.Error).failure is UserFailure.UserLocalError)
            return ResultData.Error(
                UserFailure.GetUserRepositoryError(
                    R.string.exception_occurred_local
                )
            )

//        return Error((userLocal as Error).failure)
        return ResultData.Error(
            UserFailure.GetUserRepositoryError(
                R.string.error_fetching_from_remote_and_local
            )
        )
    }

    private fun refreshCache(users: List<UserModel>) {
        cachedUsers?.clear()

        users.sortedBy { it.firstName }.forEach {
            cacheAndPerform(it) {}
        }
    }

    private fun refreshCache(user: UserModel) {
        cachedUsers?.remove(user.id)

        cacheAndPerform(user) {}
    }

    private suspend fun refreshLocalDataSource(users: List<UserModel>) {

        return // TODO: REMOVE THIS LINE TO REFRESH LOCAL DATA SOURCE

        users.forEach {
            val params = UserParams(id = it.id)

            userLocalDataSource.deleteUser(params = params)

            userLocalDataSource.saveUser(user = it)
        }
    }

    private suspend fun refreshLocalDataSource(user: UserModel) {

        val params = UserParams(id = user.id)

        userLocalDataSource.deleteUser(params = params)

        userLocalDataSource.saveUser(user = user)
    }

    private fun cacheUser(user: UserModel): UserModel {

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

        val hair = HairModel(
            color = user.hair.color,
            type = user.hair.type
        )

        val coordinatesAddress = CoordinatesModel(
            lat = user.address.coordinates.lat,
            lng = user.address.coordinates.lng,
        )

        val coordinatesCompany = CoordinatesModel(
            lat = user.company.address.coordinates.lat,
            lng = user.company.address.coordinates.lng,
        )

        val addressHome = AddressModel(
            address = user.address.address,
            city = user.address.city,
            state = user.address.state,
            stateCode = user.address.stateCode,
            postalCode = user.address.postalCode,
            country = user.address.country,
            coordinates = coordinatesAddress,
        )

        val addressCompany = AddressModel(
            address = user.company.address.address,
            city = user.company.address.city,
            state = user.company.address.state,
            stateCode = user.company.address.stateCode,
            postalCode = user.company.address.postalCode,
            country = user.company.address.country,
            coordinates = coordinatesCompany,
        )

        val bank = BankModel(
            cardExpire = user.bank.cardExpire,
            cardNumber = user.bank.cardNumber,
            cardType = user.bank.cardType,
            currency = user.bank.currency,
            iban = user.bank.iban,
        )

        val company = CompanyModel(
            department = user.company.department,
            name = user.company.name,
            title = user.company.title,
            address = addressCompany,
        )

        val crypto = CryptoModel(
            coin = user.crypto.coin,
            wallet = user.crypto.wallet,
            network = user.crypto.network,
        )


        val cachedUser = UserModel.newInstance(
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

        // Create if it doesn't exist.
        if (cachedUsers == null) {
            cachedUsers = ConcurrentHashMap()
        }

//        cachedUsers?.put(cachedUser.title, cachedUser)
        cachedUsers?.put(cachedUser.id, cachedUser)

        return cachedUser
    }

    private inline fun cacheAndPerform(user: UserModel, perform: (UserModel) -> Unit) {

        val cachedUser = cacheUser(user)

        perform(cachedUser)
    }
}
