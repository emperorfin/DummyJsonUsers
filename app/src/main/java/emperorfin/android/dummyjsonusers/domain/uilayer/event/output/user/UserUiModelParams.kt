package emperorfin.android.dummyjsonusers.domain.uilayer.event.output.user


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


interface UserUiModelParams : UserModelParams {
    val about: String?
    val keywords: List<String>?
}