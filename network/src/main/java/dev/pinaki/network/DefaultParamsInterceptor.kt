package dev.pinaki.network

import dev.pinaki.network.di.ApiKey
import dev.pinaki.network.di.DefaultLanguage
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DefaultParamsInterceptor @Inject constructor(
    @ApiKey private val apiKey: String,
    @DefaultLanguage private val defaultLanguage: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("language", defaultLanguage)
            .build()

        val requestWithApiKey = originalRequest.newBuilder()
            .url(modifiedUrl)
            .build()

        return chain.proceed(requestWithApiKey)
    }
}