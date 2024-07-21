package emperorfin.android.dummyjsonusers.domain.model.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.AddressParams
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CoordinatesParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class AddressModel(
    override val address: String,
    override val city: String,
    override val state: String,
    override val stateCode: String,
    override val postalCode: String,
    override val coordinates: CoordinatesModel,
    override val country: String
) : AddressParams
