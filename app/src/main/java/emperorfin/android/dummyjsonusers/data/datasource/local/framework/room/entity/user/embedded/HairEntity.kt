package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.HairParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class HairEntity(
    override val color: String,
    override val type: String
) : HairParams
