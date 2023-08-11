package dev.pinaki.medialist

import androidx.annotation.StringRes
import dev.pinaki.arch.Async
import dev.pinaki.model.MediaResult

data class MediaListUiState(
    val isLoading: Boolean = false,
    @StringRes val errorMessage: Int? = null,
    val trendingList: List<MediaUiState> = emptyList()
)

fun Async<MediaResult>.mapToUiState(
    @StringRes errorMessage: Int
): MediaListUiState {
    return when (this) {
        is Async.Error -> MediaListUiState(
            errorMessage = errorMessage
        )

        is Async.Success -> MediaListUiState(
            trendingList = data.results.map { media ->
                MediaUiState(
                    title = media.title.ifBlank { media.originalTitle },
                    id = media.id
                )
            }
        )

        Async.Loading -> MediaListUiState(isLoading = true)
    }
}

data class MediaUiState(
    val id: Int,
    val title: String
)