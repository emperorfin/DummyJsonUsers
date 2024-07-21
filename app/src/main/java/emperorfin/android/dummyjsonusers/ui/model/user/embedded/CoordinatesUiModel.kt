package emperorfin.android.dummyjsonusers.ui.model.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CoordinatesParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class CoordinatesUiModel(
    override val lat: Double,
    override val lng: Double
) : CoordinatesParams
