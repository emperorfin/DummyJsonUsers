package emperorfin.android.dummyjsonusers.ui.navigation

import androidx.navigation.NavHostController
import emperorfin.android.dummyjsonusers.ui.navigation.Destinations.ROUTE_USERS
import emperorfin.android.dummyjsonusers.ui.navigation.Destinations.ROUTE_USER_DETAILS
import emperorfin.android.dummyjsonusers.ui.navigation.Screens.SCREEN_USERS
import emperorfin.android.dummyjsonusers.ui.navigation.Screens.SCREEN_USER_DETAILS


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */



/**
 * Screens used in [Destinations]
 */
private object Screens {
    const val SCREEN_USERS: String = "users"
    const val SCREEN_USER_DETAILS: String = "userdetails"
}

/**
 * Destinations used in the [MainActivity]
 */
object Destinations {
    const val ROUTE_USERS: String = SCREEN_USERS
    const val ROUTE_USER_DETAILS: String = SCREEN_USER_DETAILS
}

object ScreenArgs {
    const val SCREEN_USER_DETAILS_USER_ID: String = "userid"
}

/**
 * Models the navigation actions in the app.
 */
class NavigationActions(private val navController: NavHostController) {

    fun navigateToUsersScreen() {
        navController.navigate(ROUTE_USERS)
    }

    /**
     * args: "{id}"
     */
    fun navigateToUserDetailsScreen(args: String) {

        val routeWithArgs = "${ROUTE_USER_DETAILS}/$args"

        navController.navigate(routeWithArgs)
    }

    fun navigateBack() {
//        navController.popBackStack() // Still works
        navController.navigateUp()
    }

}