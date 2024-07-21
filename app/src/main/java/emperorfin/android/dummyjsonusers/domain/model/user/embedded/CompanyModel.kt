package emperorfin.android.dummyjsonusers.domain.model.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.AddressParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CompanyParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class CompanyModel(
    override val department: String,
    override val name: String,
    override val title: String,
    override val address: AddressModel
) : CompanyParams
