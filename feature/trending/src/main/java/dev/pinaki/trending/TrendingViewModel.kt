package dev.pinaki.trending

import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.pinaki.arch.Async
import dev.pinaki.medialist.MediaListUiState
import dev.pinaki.medialist.mapToUiState
import dev.pinaki.model.MediaResult
import dev.pinaki.util.StringResource
import dev.pinaki.util.WhileUiSubscribed
import dev.pinaki.util.di.DefaultDispatcher
import dev.pinaki.util.passTo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val trendingRepository: TrendingRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _trendingMedia = MutableStateFlow<Async<MediaResult>>(Async.Loading)
    val uiState: StateFlow<MediaListUiState> = _trendingMedia
        .map {
            withContext(defaultDispatcher) {
                it.mapToUiState(
                    errorMessage = R.string.an_error_occurred,
                    ratingText = { rating, totalVotes ->
                        StringResource.StringIdResource(
                            id = R.string.rating,
                            formatArgs = listOf(rating, totalVotes)
                        )

                    }
                )
            }
        }
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