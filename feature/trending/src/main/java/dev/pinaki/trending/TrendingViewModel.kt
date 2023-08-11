package dev.pinaki.trending

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pinaki.arch.Async
import dev.pinaki.model.MediaResult
import dev.pinaki.util.WhileUiSubscribed
import dev.pinaki.util.passTo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val trendingRepository: TrendingRepository,
) : ViewModel() {

    private val _trendingMedia = MutableStateFlow<Async<MediaResult>>(Async.Loading)
    val uiState: StateFlow<TrendingUiState> = _trendingMedia
        .map { mapToUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = TrendingUiState(isLoading = true)
        )

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            trendingRepository.getTrendingMedia()
                .passTo(_trendingMedia)
        }
    }
}

private fun mapToUiState(result: Async<MediaResult>): TrendingUiState {
    return when (result) {
        is Async.Error -> TrendingUiState(
            errorMessage = R.string.an_error_occurred
        )

        is Async.Success -> TrendingUiState(
            trendingList = result.data.results.map { media ->
                MediaUiState(
                    title = media.title.ifBlank { media.originalTitle },
                    id = media.id
                )
            }
        )

        Async.Loading -> TrendingUiState(isLoading = true)
    }
}

data class TrendingUiState(
    val isLoading: Boolean = false,
    @StringRes val errorMessage: Int? = null,
    val trendingList: List<MediaUiState> = emptyList()
)

data class MediaUiState(
    val id: Int,
    val title: String
)