package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CoordinatesParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class CoordinatesDataTransferObject(
    override val lat: Double,
    override val lng: Double
) : CoordinatesParams
