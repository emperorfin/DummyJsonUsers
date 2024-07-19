package emperorfin.android.dummyjsonusers.temp


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */


data class Address(
    var address: String,
    var city: String,
    var state: String,
    var stateCode: String,
    var postalCode: String,
    var coordinates: Coordinates,
    var country: String,
)
