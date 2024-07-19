package emperorfin.android.dummyjsonusers.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Friday 19th July, 2024.
 */



@Composable
fun LoadingContent(
    loading: Boolean,
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    loadingIndicator: @Composable () -> Unit,
    content: @Composable () -> Unit = {}
) {
    if (empty) {
        emptyContent()
    } else if (loading) {
        loadingIndicator()
    } else {

        Box {

            content()

        }
    }

}