package emperorfin.android.dummyjsonusers.ui.extension

import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import emperorfin.android.dummyjsonusers.data.datasource.remote.framework.retrofit.webservice.dummyjson.api.UserApi
import emperorfin.android.dummyjsonusers.ui.model.user.UserUiModel
import kotlinx.coroutines.flow.StateFlow


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Sunday 21st July, 2024.
 */



inline fun <T> LazyGridScope.paging(
    items: List<T>,
    newUsersLength: StateFlow<Int>,
    threshold: Int = 4,
//    pageSize: Int = UserApi.PAGE_LIMIT,
    crossinline fetch: () -> Unit,
//    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit,
    crossinline itemContent: @Composable (index: Int, item: T) -> Unit,
) {
    val _newUsersLength = newUsersLength.value

    itemsIndexed(items) { index, item ->

        android.util.Log.d("LazyItemExtension", "paging 1")

        itemContent(index, item)

        android.util.Log.d("LazyItemExtension", "paging 2")
        android.util.Log.d("LazyItemExtension", "index: $index")
        android.util.Log.d("LazyItemExtension", "newUsersLength: $_newUsersLength")
        android.util.Log.d("LazyItemExtension", "item name: ${(item as UserUiModel).firstName} ${(item as UserUiModel).lastName}")

//        if ((index + threshold + 1) >= pageSize * (currentIndex - 1)) {
        if ((index + threshold + 1) >= _newUsersLength) {
//        if (index >= 11) {

            fetch()

        }
    }
}