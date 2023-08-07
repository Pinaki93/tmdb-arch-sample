package dev.pinaki.tmdbarchsample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pinaki.network.di.TmdbRetrofit
import dev.pinaki.tmdbservice.TrendingService
import dev.pinaki.tmdbarchsample.util.service
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun providesTrendingService(@TmdbRetrofit retrofit: Retrofit): TrendingService =
        retrofit.service()
}