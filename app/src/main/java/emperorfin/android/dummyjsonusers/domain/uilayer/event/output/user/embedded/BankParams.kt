package emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user.embedded


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


interface BankParams {
    val cardExpire: String?
    val cardNumber: String?
    val cardType: String?
    val currency: String?
    val iban: String?
}