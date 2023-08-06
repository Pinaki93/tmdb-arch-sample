package dev.pinaki.tmdbarchsample.network

import dev.pinaki.tmdbarchsample.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DefaultParamsInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", BuildConfig.DEFAULT_LANGUAGE)
            .build()

        val requestWithApiKey = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()

        return chain.proceed(requestWithApiKey)
    }
}