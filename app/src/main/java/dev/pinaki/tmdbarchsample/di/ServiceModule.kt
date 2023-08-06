package dev.pinaki.tmdbarchsample.di

import dev.pinaki.network.di.TmdbRetrofit
import dev.pinaki.tmdbservice.TrendingService
import dev.pinaki.tmdbarchsample.util.service
import retrofit2.Retrofit

object ServiceModule {

    fun providesTrendingService(@TmdbRetrofit retrofit: Retrofit): TrendingService =
        retrofit.service()
}