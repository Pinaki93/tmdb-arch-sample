package dev.pinaki.tmdbarchsample.data.remote

import dev.pinaki.tmdbarchsample.data.remote.model.MediaResponse
import retrofit2.http.GET

interface TrendingService {
    @GET("trending/all/day")
    suspend fun getTrendingMedia(): MediaResponse
}