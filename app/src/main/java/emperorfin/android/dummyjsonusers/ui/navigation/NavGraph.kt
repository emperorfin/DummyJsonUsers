package emperorfin.android.dummyjsonusers.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.insets.ProvideWindowInsets
import emperorfin.android.dummyjsonusers.ui.navigation.Destinations.ROUTE_USERS
import emperorfin.android.dummyjsonusers.ui.navigation.Destinations.ROUTE_USER_DETAILS
import emperorfin.android.dummyjsonusers.ui.navigation.ScreenArgs.SCREEN_USER_DETAILS_USER_ID
import emperorfin.android.dummyjsonusers.ui.screen.userdetails.UserDetailsScreen
import emperorfin.android.dummyjsonusers.ui.screen.users.UsersScreen


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */



@Composable
fun NavGraph(
    context: Context = LocalContext.current,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_USERS,
    navActions: NavigationActions = remember(navController) {
        NavigationActions(navController)
    },
) {

    val userDetailsRouteWithArgs = "${ROUTE_USER_DETAILS}/{$SCREEN_USER_DETAILS_USER_ID}"

    ProvideWindowInsets {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination,
        ) {

            composable(ROUTE_USERS) {
                UsersScreen(navigationActions = navActions)
            }

            composable(
                userDetailsRouteWithArgs,
                arguments = listOf(                                         // declaring argument type
                    navArgument(SCREEN_USER_DETAILS_USER_ID) { type = NavType.StringType },
                )
            ) { backStackEntry ->

                val userId: String = backStackEntry.arguments?.getString(SCREEN_USER_DETAILS_USER_ID)!!

                UserDetailsScreen(
                    navigationActions = navActions,
                    userId = userId,
                )
            }
        }
    }

}