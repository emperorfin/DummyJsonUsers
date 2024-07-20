package emperorfin.android.dummyjsonusers.ui.screen.userdetails

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.google.accompanist.flowlayout.FlowRow
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.palette.BitmapPalette
import emperorfin.android.dummyjsonusers.R
import emperorfin.android.dummyjsonusers.temp.User
import emperorfin.android.dummyjsonusers.ui.component.AppBarWithArrow
import emperorfin.android.dummyjsonusers.ui.component.EmptyContent
import emperorfin.android.dummyjsonusers.ui.component.LoadingContent
import emperorfin.android.dummyjsonusers.ui.component.LoadingIndicator
import emperorfin.android.dummyjsonusers.ui.component.NetworkImage
import emperorfin.android.dummyjsonusers.ui.navigation.NavigationActions
import emperorfin.android.dummyjsonusers.ui.screen.userdetails.stateholders.UserDetailsUiState
import emperorfin.android.dummyjsonusers.ui.screen.userdetails.stateholders.UserDetailsViewModel
import emperorfin.android.dummyjsonusers.ui.theme.Purple40
import emperorfin.android.dummyjsonusers.ui.theme.background


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */



@Composable
fun UserDetailsScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    navigationActions: NavigationActions?,
    userId: String,
    viewModel: UserDetailsViewModel = UserDetailsViewModel(),
//    viewModel: UserDetailsViewModel = hiltViewModel(),
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val uiState = UserDetailsUiState()
//    val uiState by viewModel.uiState.collectAsState()

    val user = uiState.user

//    LaunchedEffect(key1 = userId) {
//        viewModel.loadUser(
//            params = UserParams(id = userId),
//            isRefresh = false
//        )
//    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AppBarWithArrow(
                title = "${user?.firstName} ${user?.lastName}",
                onBackPress = {
                    navigationActions?.navigateBack()
                }
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->

        Content(
            modifier = Modifier.padding(paddingValues),
            userId = userId,
            user = user,
            viewModel = viewModel,
            uiState = uiState
        )

        // Check for SnackBar messages to display on the screen
//        uiState.messageSnackBar?.let { message ->
//            val snackBarText = stringResource(message)
//            LaunchedEffect(snackbarHostState, viewModel, message, snackBarText) {
//                snackbarHostState.showSnackbar(message = snackBarText)
//                viewModel.snackBarMessageShown()
//            }
//        }

    }
}

@Composable
private fun Content(
    modifier: Modifier,
    userId: String,
    user: User?,
//    user: UserUiModel?,
    viewModel: UserDetailsViewModel,
    uiState: UserDetailsUiState
) {

    val isLoading = uiState.isLoading
    val errorMessage = uiState.errorMessage

    LoadingContent(
        loading = isLoading,
        empty = user == null && !isLoading,
        emptyContent = {
            EmptyContent(
                errorLabel = errorMessage ?: R.string.content_description_error_message,
                onRetry = {
//                    viewModel.loadUser(
//                        params = UserParams(id = userId),
//                        isRefresh = false
//                    )
                }
            )
        },
        loadingIndicator = {
            LoadingIndicator(modifier = modifier)
        }
    ) {


        user?.let {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .background(background)
                    .fillMaxSize(),
            ) {

                Spacer(modifier = Modifier.height(58.dp))

                Header(it)

                Summary(it)

                OtherDetails(it)

                Spacer(modifier = Modifier.height(24.dp))
            }

        }

    }
}

@Composable
private fun Header(
    user: User
//    user: UserUiModel
) {

    Column {

        var palette by remember { mutableStateOf<Palette?>(null) }
        NetworkImage(
            networkUrl = user.image,
            circularReveal = CircularReveal(duration = 300),
            shimmerParams = null,
            bitmapPalette = BitmapPalette {
                palette = it
            },
            modifier = Modifier
                .height(280.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "${user.firstName} ${user.lastName}",
//            style = MaterialTheme.typography.h5,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "@${user.username}",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Birth Date: ${user.birthDate}",
//            style = MaterialTheme.typography.body1,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Role: ${user.role}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

    }
}

@Composable
private fun Summary(
    user: User
//    user: UserUiModel
) {

    val gender = user.gender
    val heOrShe = if (gender == "male") "He" else "She"
    val firstName = user.firstName
    val address = user.address.address
    val city = user.address.city
    val state = user.address.state
    val country = user.address.country

    val university = user.university

    val companyJobTitle = user.company.title
    val companyDept = user.company.department
    val companyName = user.company.name
    val companyAddress = user.company.address.address
    val companyCity = user.company.address.city
    val companyState = user.company.address.state
    val companyCountry = user.company.address.country

    val aboutUser = "$firstName is from $address, $city, $state, $country. " +
            "$heOrShe studied at $university. $heOrShe works as a" +
            " $companyJobTitle under the department of $companyDept at " +
            "$companyName which is located at $companyAddress, $companyCity, $companyState, $companyCountry."

    val age = "age: ${user.age}"
    val bloodgroup = "bloodgroup: ${user.bloodGroup}"
    val height = "height: ${user.height}"
    val weight = "weight: ${user.weight}"
    val eyeColor = "${user.eyeColor.lowercase()} eye"
    val hair = "${user.hair.type.lowercase()} ${user.hair.color.lowercase()} hair"

    val keywords = listOf(
        gender,
        age,
        bloodgroup,
        height,
        weight,
        eyeColor,
        hair
    )

    Column {

        Spacer(modifier = Modifier.height(23.dp))

        Text(
            text = stringResource(R.string.about),
//            style = MaterialTheme.typography.h6,
//            style = MaterialTheme.typography.headlineLarge,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = aboutUser,
//            style = MaterialTheme.typography.body1,
//            style = MaterialTheme.typography.bodyLarge,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        FlowRow {

            keywords.forEach {

                Keyword(it.trim())
            }
        }
    }
}

@Composable
private fun Keyword(keyword: String) {
    Surface(
        shape = RoundedCornerShape(32.dp),
//        elevation = 8.dp,
        shadowElevation = 8.dp,
        color = Purple40,
        modifier = Modifier.padding(8.dp)
    ) {

        Text(
            text = keyword,
//            style = MaterialTheme.typography.body1,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun OtherDetails(
    user: User
//    user: UserUiModel
) {

    val contact = "${user.email} \n${user.phone}"
    val bank = "Card number: ${user.bank.cardNumber} \nCard expiry: ${user.bank.cardExpire} \nIBAN: ${user.bank.iban}"
    val cryptocurrency = "Coin: ${user.crypto.coin} \nWallet: ${user.crypto.wallet} \nNetwork: ${user.crypto.network}"

    Column {

        Spacer(modifier = Modifier.height(23.dp))

        Text(
            text = stringResource(R.string.other_details),
//                style = MaterialTheme.typography.h6,
//                style = MaterialTheme.typography.headlineLarge,
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )

        Column {
            Detail(
                title = "Contact",
                subtitle = contact,
            )

            Detail(
                title = "Bank",
                subtitle = bank,
            )

            Detail(
                title = "Cryptocurrency",
                subtitle = cryptocurrency,
            )

            Detail(
                title = "User agent",
                subtitle = user.userAgent,
            )
        }
    }
}

@Composable
private fun Detail(
    title: String,
    subtitle: String,
) {

    Column {

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = title,
//            style = MaterialTheme.typography.body1,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = subtitle,
//            style = MaterialTheme.typography.body2,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
    }
}