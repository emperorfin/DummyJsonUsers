package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.AddressParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class AddressDataTransferObject(
    override val address: String,
    override val city: String,
    override val state: String,
    override val stateCode: String,
    override val postalCode: String,
    override val coordinates: CoordinatesDataTransferObject,
    override val country: String
) : AddressParams
