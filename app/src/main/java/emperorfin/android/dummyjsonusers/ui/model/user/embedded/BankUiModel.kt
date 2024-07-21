package emperorfin.android.dummyjsonusers.ui.model.user.embedded

import emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded.BankParams


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


data class BankUiModel(
    override val cardExpire: String,
    override val cardNumber: String,
    override val cardType: String,
    override val currency: String,
    override val iban: String
) : BankParams
