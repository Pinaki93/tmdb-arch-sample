package dev.pinaki.trending

import dev.pinaki.arch.Async
import dev.pinaki.model.MediaResult
import dev.pinaki.tmdbservice.TrendingService
import dev.pinaki.trending.mapper.TrendingResponseMapper
import dev.pinaki.util.runAsync
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val trendingService: TrendingService,
    private val trendingResponseMapper: TrendingResponseMapper,
) {

    fun getTrendingMedia(): Flow<Async<MediaResult>> = runAsync {
        trendingService.getTrendingMedia().let {
            trendingResponseMapper.map(it)
        }
    }
}