package dev.pinaki.trending

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.pinaki.arch.Async
import dev.pinaki.medialist.MediaListUiState
import dev.pinaki.medialist.mapToUiState
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
    val uiState: StateFlow<MediaListUiState> = _trendingMedia
        .map { it.mapToUiState(R.string.an_error_occurred) }
        .stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = MediaListUiState(isLoading = true)
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