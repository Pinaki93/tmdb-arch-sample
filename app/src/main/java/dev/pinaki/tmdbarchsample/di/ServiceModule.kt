package dev.pinaki.tmdbarchsample.di

import dev.pinaki.tmdbarchsample.data.remote.TrendingService
import dev.pinaki.tmdbarchsample.util.service
import retrofit2.Retrofit

object ServiceModule {

    fun providesTrendingService(@TmdbRetrofit retrofit: Retrofit): TrendingService =
        retrofit.service()
}