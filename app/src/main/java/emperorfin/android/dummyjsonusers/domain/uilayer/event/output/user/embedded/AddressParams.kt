package emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded

import emperorfin.android.dummyjsonusers.temp.Coordinates


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


interface AddressParams {
    val address: String?
    val city: String?
    val state: String?
    val stateCode: String?
    val postalCode: String?
    val coordinates: CoordinatesParams?
    val country: String?
}