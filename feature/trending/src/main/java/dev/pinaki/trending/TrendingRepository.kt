package dev.pinaki.trending

import dev.pinaki.arch.Async
import dev.pinaki.model.MediaResult
import dev.pinaki.tmdbservice.TrendingService
import dev.pinaki.util.asyncFlow
import dev.pinaki.util.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val trendingService: TrendingService,
    private val trendingResponseMapper: TrendingResponseMapper,
) {

    fun getTrendingMedia(): Flow<Async<MediaResult>> = asyncFlow(dispatcher) {
        trendingService.getTrendingMedia().let {
            trendingResponseMapper.map(it)
        }
    }
}