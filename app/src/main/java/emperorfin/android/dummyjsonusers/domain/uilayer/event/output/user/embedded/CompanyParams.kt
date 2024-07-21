package emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded

import emperorfin.android.dummyjsonusers.temp.Address


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


interface CompanyParams {
    val department: String?
    val name: String?
    val title: String?
    val address: AddressParams?
}