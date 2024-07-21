package emperorfin.android.dummyjsonusers.data.datasource.local.framework.room.entity.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CryptoParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class CryptoEntity(
    override val coin: String,
    override val wallet: String,
    override val network: String
) : CryptoParams
