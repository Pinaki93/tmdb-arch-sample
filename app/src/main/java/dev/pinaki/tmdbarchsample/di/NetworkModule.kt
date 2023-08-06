package dev.pinaki.tmdbarchsample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pinaki.tmdbarchsample.network.DefaultParamsInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TmdbRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthOkHttpClient


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @AuthOkHttpClient
    fun providesOkHttpClient(defaultParamsInterceptor: DefaultParamsInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(defaultParamsInterceptor)
            .readTimeout(30, SECONDS)
            .writeTimeout(30, SECONDS)
            .connectTimeout(30, SECONDS)
            .build()

    @Provides
    @Singleton
    @TmdbRetrofit
    fun providesTmdbRetrofit(
        @AuthOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}