package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CompanyParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class CompanyDataTransferObject(
    override val department: String,
    override val name: String,
    override val title: String,
    override val address: AddressDataTransferObject
) : CompanyParams
