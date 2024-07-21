package emperorfin.android.dummyjsonusers.ui.model.user.embedded

import emperorfin.android.dummyjsonusers.domain.model.user.embedded.CoordinatesModel
import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.AddressParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class AddressUiModel(
    override val address: String,
    override val city: String,
    override val state: String,
    override val stateCode: String,
    override val postalCode: String,
    override val coordinates: CoordinatesUiModel,
    override val country: String
) : AddressParams
