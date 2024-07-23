package emperorfin.android.dummyjsonusers.ui.screen.userdetails.stateholders

import emperorfin.android.dummyjsonusers.temp.SampleUsers
import emperorfin.android.dummyjsonusers.temp.User
import emperorfin.android.dummyjsonusers.ui.model.user.UserUiModel


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */


data class UserDetailsUiState(
    val user: UserUiModel? = null,
//    val user: User? = SampleUsers.getUsers()[0],
    val isLoading: Boolean = false,
    val errorMessage: Int? = null,
    val messageSnackBar: Int? = null,
)
