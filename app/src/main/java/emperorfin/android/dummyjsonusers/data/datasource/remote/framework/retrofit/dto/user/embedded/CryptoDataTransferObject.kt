package emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.dto.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.CryptoParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class CryptoDataTransferObject(
    override val coin: String,
    override val wallet: String,
    override val network: String
) : CryptoParams
