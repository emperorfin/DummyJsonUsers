package emperorfin.android.dummyjsonusers.temp


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */


data class User(
    var id: Int,
    var firstName: String,
    var lastName: String,
    var maidenName: String,
    var age: Int,
    var gender: String,
    var email: String,
    var phone: String,
    var username: String,
    var password: String,
    var birthDate: String,
    var image: String,
    var bloodGroup: String,
    var height: Double,
    var weight: Double,
    var eyeColor: String,
    var hair: Hair,
    var ip: String,
    var address: Address,
    var macAddress: String,
    var university: String,
    var bank: Bank,
    var company: Company,
    var ein: String,
    var ssn: String,
    var userAgent: String,
    var crypto: Crypto,
    var role: String,
)
