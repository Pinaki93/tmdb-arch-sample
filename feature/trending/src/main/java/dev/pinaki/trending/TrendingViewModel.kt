package dev.pinaki.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pinaki.arch.Async
import dev.pinaki.model.Media
import dev.pinaki.model.MediaResult
import dev.pinaki.util.WhileUiSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    trendingRepository: TrendingRepository,
) : ViewModel() {

    val uiState: StateFlow<TrendingUiState> = trendingRepository.getTrendingMedia()
        .map(::mapToUiState)
        .stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = TrendingUiState(isLoading = true)
        )
}

private fun mapToUiState(result: Async<MediaResult>): TrendingUiState {
    return when (result) {
        is Async.Error -> TrendingUiState(
            errorMessage = "An Error Occurred"/*TODO: add string resource*/
        )

        is Async.Success -> TrendingUiState(
            trendingList = result.data.results
        )
    }
}

data class TrendingUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val trendingList: List<Media> = emptyList()
)