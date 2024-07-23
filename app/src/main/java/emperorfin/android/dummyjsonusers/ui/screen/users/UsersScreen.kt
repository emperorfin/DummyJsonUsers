package emperorfin.android.dummyjsonusers.ui.screen.users

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import emperorfin.android.dummyjsonusers.R
import emperorfin.android.dummyjsonusers.domain.uilayer.event.input.UserParams
import emperorfin.android.dummyjsonusers.ui.component.AppBar
import emperorfin.android.dummyjsonusers.ui.component.EmptyContent
import emperorfin.android.dummyjsonusers.ui.component.LoadingContent
import emperorfin.android.dummyjsonusers.ui.component.LoadingIndicator
import emperorfin.android.dummyjsonusers.ui.component.UserListItem
import emperorfin.android.dummyjsonusers.ui.extension.paging
import emperorfin.android.dummyjsonusers.ui.model.user.UserUiModel
import emperorfin.android.dummyjsonusers.ui.navigation.NavigationActions
import emperorfin.android.dummyjsonusers.ui.screen.users.stateholders.UsersUiState
import emperorfin.android.dummyjsonusers.ui.screen.users.stateholders.UsersViewModel
import emperorfin.android.dummyjsonusers.ui.util.InternetConnectivityUtil.hasInternetConnection


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    navigationActions: NavigationActions?,
//    viewModel: UsersViewModel = UsersViewModel(),
    viewModel: UsersViewModel = hiltViewModel(),
) {

    val snackbarHostState = remember { SnackbarHostState() }

    val uiState by viewModel.uiState.collectAsState()
//    val uiState = UsersUiState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { AppBar() },
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->

        Content(
            modifier = Modifier.padding(paddingValues),
            navigationActions = navigationActions,
            viewModel = viewModel,
            uiState = uiState
        )

//         Check for SnackBar messages to display on the screen
        uiState.messageSnackBar?.let { message ->
            val snackBarText = stringResource(message)
            LaunchedEffect(snackbarHostState, viewModel, message, snackBarText) {
                snackbarHostState.showSnackbar(message = snackBarText)
                viewModel.snackBarMessageShown()
            }
        }

    }
}

@Composable
private fun Content(
    modifier: Modifier,
    navigationActions: NavigationActions?,
    context: Context = LocalContext.current,
    viewModel: UsersViewModel,
    uiState: UsersUiState
) {

    var lazyListState = rememberLazyGridState()
//    val scope = rememberCoroutineScope()
//    var lazyListStateAlreadyScrolled: Boolean = remember { false }
//
//    var clickedItemIndex: Int by rememberSaveable { mutableStateOf(0)}
//
//    LaunchedEffect(key1 = scope) {
//
////        if (!lazyListStateAlreadyScrolled) {
////            lazyListStateAlreadyScrolled = true
////
////            scope.launch { lazyListState.scrollToItem(9) }
////        } else {
////            scope.launch { lazyListState.scrollToItem(9) }
////        }
//
////        scope.launch { lazyListState.scrollToItem(index = clickedItemIndex, scrollOffset = 0) }
//
//        clickedItemIndex -= 8
//
////        if (clickedItemIndex < 0) clickedItemIndex = 0
//        if (clickedItemIndex < 0) clickedItemIndex *= -1
//
//        scope.launch { lazyListState.scrollToItem(index = clickedItemIndex) }
//    }

    val latestPaginatedUsers = uiState.users
    val totalPaginatedUsersRetrieved = mutableListOf<UserUiModel>()
    val isLoading = uiState.isLoading
    val errorMessage = uiState.errorMessage

    totalPaginatedUsersRetrieved.addAll(latestPaginatedUsers)

    Column(
        modifier = Modifier
//            .statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(58.dp))

        LoadingContent(
            loading = isLoading,
//            empty = users.isEmpty() && !isLoading,
            empty = totalPaginatedUsersRetrieved.isEmpty() && !isLoading,
            emptyContent = {
                EmptyContent(
                    errorLabel = errorMessage ?: R.string.content_description_error_message,
                    onRetry = {

                        val skip: Int = viewModel.newUsersLength.value

                        viewModel.loadUsers(
                            params = UserParams(othersArgs = mapOf("skip" to skip.toString())),
                            isRefresh = false
                        )
                    }
                )
            },
            loadingIndicator = {
                LoadingIndicator(modifier = modifier)
            }
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
//                state = rememberLazyGridState(),
                state = lazyListState,
                modifier = Modifier
//                    .statusBarsPadding()
//                    .offset(0.dp, (-58).dp)
                    .background(MaterialTheme.colorScheme.background),
//                contentPadding = PaddingValues(
//                    start = 12.dp,
//                    end = 12.dp,
//                    bottom = 16.dp
//                )
            ) {

//                itemsIndexed(users) { index, user ->
//
//                    UserListItem(
//                        user = user,
//                        onClick = {
//
//                            if (!hasInternetConnection(context.applicationContext as Application)){
//                                Toast.makeText(context, R.string.message_no_internet_connectivity, Toast.LENGTH_SHORT).show()
//
//                                return@UserListItem
//                            }
//
////                            navigationActions?.navigateToUserDetailsScreen(user.id.toString()) // Works
//                            navigationActions?.navigateToUserDetailsScreen(it.toString())
//
//                            //remv
////                            Toast.makeText(
////                                context,
////                                it.toString(), // Or user.id.toString()
////                                Toast.LENGTH_SHORT
////                            ).show()
//                        }
//                    )
//                }

                paging(
                    items = totalPaginatedUsersRetrieved,
                    newUsersLength = viewModel.newUsersLength,
                    fetch = {
                        viewModel.fetchNextUserPage()
                    }
                ) { index, item ->

                    UserListItem(
                        user = item,
                        onClick = {

//                            clickedItemIndex = it.toInt()
//                            clickedItemIndex = index

                            if (!hasInternetConnection(context.applicationContext as Application)){
                                Toast.makeText(context, R.string.message_no_internet_connectivity, Toast.LENGTH_SHORT).show()

                                return@UserListItem
                            }

//                            navigationActions?.navigateToUserDetailsScreen(user.id.toString()) // Works
                            navigationActions?.navigateToUserDetailsScreen(it.toString())

                            //remv
//                            Toast.makeText(
//                                context,
//                                it.toString(), // Or user.id.toString()
//                                Toast.LENGTH_SHORT
//                            ).show()
                        }
                    )
                }

                //remv
//                CoroutineScope(Dispatchers.Main).launch {
////                    state.snapToItemIndex(0, 0)
////                    scope.launch { lazyListState.scrollToItem(9) }
//                    lazyListState.scrollToItem(9)
//                }

            }

        }

    }
}