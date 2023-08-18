package dev.pinaki.medialist

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import dev.pinaki.arch.Async
import dev.pinaki.model.MediaResult
import dev.pinaki.util.StringResource

@Immutable
data class MediaListUiState(
    val isLoading: Boolean = false,
    @StringRes val errorMessage: Int? = null,
    val trendingList: List<MediaUiState> = emptyList()
)

inline fun Async<MediaResult>.mapToUiState(
    @StringRes errorMessage: Int,
    ratingText: (rating: Double, totalVotes: Int) -> StringResource,
): MediaListUiState {
    return when (this@mapToUiState) {
        is Async.Error -> MediaListUiState(
            errorMessage = errorMessage
        )

        is Async.Success -> MediaListUiState(
            trendingList = data.results.map { media ->
                with(media) {
                    MediaUiState(
                        id = id,
                        title = title.ifBlank { media.originalTitle },
                        language = originalLanguage,
                        ratingText = ratingText(voteAverage, voteCount),
                        posterUrl = media.posterUrl()
                    )
                }
            }
        )

        Async.Loading -> MediaListUiState(isLoading = true)
    }
}

data class MediaUiState(
    val id: Int,
    val title: String,
    val language: String,
    val ratingText: StringResource,
    val posterUrl: String,
)