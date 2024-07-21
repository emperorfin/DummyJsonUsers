package emperorfin.android.dummyjsonusers.ui.model.user.embedded

import emperorfin.android.dummyjsonusers.domain.model.user.embedded.AddressModel
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CompanyParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class CompanyUiModel(
    override val department: String,
    override val name: String,
    override val title: String,
    override val address: AddressUiModel
) : CompanyParams
