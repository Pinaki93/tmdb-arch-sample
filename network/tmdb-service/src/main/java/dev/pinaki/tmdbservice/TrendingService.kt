package dev.pinaki.tmdbservice

import retrofit2.http.GET

interface TrendingService {
    @GET("trending/all/day")
    suspend fun getTrendingMedia(): MediaResponse
}