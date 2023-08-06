package dev.pinaki.tmdbarchsample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pinaki.network.di.ApiKey
import dev.pinaki.network.di.DefaultLanguage
import dev.pinaki.tmdbarchsample.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {
    @Provides
    @ApiKey
    fun providesApiKey(): String = BuildConfig.API_KEY

    @Provides
    @DefaultLanguage
    fun providesDefaultLanguage(): String = BuildConfig.DEFAULT_LANGUAGE
}