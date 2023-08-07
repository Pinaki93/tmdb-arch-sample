package dev.pinaki.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TmdbRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class AuthOkHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiKey

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultLanguage

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class OkHttpCacheSize