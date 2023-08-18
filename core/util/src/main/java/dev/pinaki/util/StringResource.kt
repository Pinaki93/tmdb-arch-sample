package dev.pinaki.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable


@Immutable
sealed class StringResource {
    @Immutable
    data class StringIdResource(
        @StringRes val id: Int,
        val formatArgs: List<Any>,
    ) : StringResource()

    data class Simple(
        val string: String,
    ) : StringResource()
}


@Composable
@ReadOnlyComposable
fun stringResource(resource: StringResource): String {
    return when (resource) {
        is StringResource.StringIdResource -> androidx.compose.ui.res.stringResource(
            id = resource.id,
            *resource.formatArgs.toTypedArray()
        )

        is StringResource.Simple -> resource.string
    }
}