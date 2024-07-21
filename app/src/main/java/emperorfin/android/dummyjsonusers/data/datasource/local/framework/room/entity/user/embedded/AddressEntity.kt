package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.AddressParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class AddressEntity(
    override val address: String,
    override val city: String,
    override val state: String,
    override val stateCode: String,
    override val postalCode: String,
    override val coordinates: CoordinatesEntity,
    override val country: String
) : AddressParams
