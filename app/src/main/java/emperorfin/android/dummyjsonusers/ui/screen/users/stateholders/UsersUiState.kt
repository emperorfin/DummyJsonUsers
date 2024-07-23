package emperorfin.android.dummyjsonusers.ui.screen.users.stateholders

import emperorfin.android.dummyjsonusers.temp.SampleUsers
import emperorfin.android.dummyjsonusers.ui.model.user.UserUiModel
import emperorfin.android.dummyjsonusers.temp.User


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */


data class UsersUiState(
//    val users: List<User> = SampleUsers.getUsers(),
    val users: List<UserUiModel> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: Int? = null,
    val messageSnackBar: Int? = null,
)
