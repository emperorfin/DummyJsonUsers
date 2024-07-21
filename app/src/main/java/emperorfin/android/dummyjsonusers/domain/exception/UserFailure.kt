package emperorfin.android.dummyjsonusers.domain.exception

import androidx.annotation.StringRes
import emperorfin.android.dummyjsonusers.R
import emperorfin.android.dummyjsonusers.domain.exception.Failure.FeatureFailure


/*
 * @Author: Francis Nwokelo (emperorfin)
 * @Date: Saturday 20th July, 2024.
 */


sealed class UserFailure(
    @StringRes open val message: Int,
    open val cause: Throwable?
) : FeatureFailure() {

    class UserListNotAvailableMemoryError(
        @StringRes override val message: Int = R.string.error_memory_user_list_not_available,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class UserListNotAvailableLocalError(
        @StringRes override val message: Int = R.string.error_local_user_list_not_available,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class UserListNotAvailableRemoteError(
        @StringRes override val message: Int = R.string.error_remote_user_list_not_available,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class UserMemoryError(
        @StringRes override val message: Int = R.string.error_memory_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class UserLocalError(
        @StringRes override val message: Int = R.string.error_local_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class UserRemoteError(
        @StringRes override val message: Int = R.string.error_remote_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class GetUserMemoryError(
        @StringRes override val message: Int = R.string.error_memory_get_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class GetUserLocalError(
        @StringRes override val message: Int = R.string.error_local_get_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class GetUserRemoteError(
        @StringRes override val message: Int = R.string.error_remote_get_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class InsertUserMemoryError(
        @StringRes override val message: Int = R.string.error_memory_insert_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class InsertUserLocalError(
        @StringRes override val message: Int = R.string.error_local_insert_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class InsertUserRemoteError(
        @StringRes override val message: Int = R.string.error_remote_insert_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class UpdateUserMemoryError(
        @StringRes override val message: Int = R.string.error_memory_update_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class UpdateUserLocalError(
        @StringRes override val message: Int = R.string.error_local_update_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class UpdateUserRemoteError(
        @StringRes override val message: Int = R.string.error_remote_update_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class DeleteUserMemoryError(
        @StringRes override val message: Int = R.string.error_memory_delete_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class DeleteUserLocalError(
        @StringRes override val message: Int = R.string.error_local_delete_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class DeleteUserRemoteError(
        @StringRes override val message: Int = R.string.error_remote_delete_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class NonExistentUserDataMemoryError(
        @StringRes override val message: Int = R.string.error_memory_non_existent_user_data,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class NonExistentUserDataLocalError(
        @StringRes override val message: Int = R.string.error_local_non_existent_user_data,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class NonExistentUserDataRemoteError(
        @StringRes override val message: Int = R.string.error_remote_non_existent_user_data,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    // For Repositories
    class GetUserRepositoryError(
        @StringRes override val message: Int = R.string.error_repository_get_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class InsertUserRepositoryError(
        @StringRes override val message: Int = R.string.error_repository_insert_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)

    class DeleteUserRepositoryError(
        @StringRes override val message: Int = R.string.error_repository_delete_user,
        override val cause: Throwable? = null
    ) : UserFailure(message = message, cause = cause)
}
