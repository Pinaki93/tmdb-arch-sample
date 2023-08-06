package dev.pinaki.tmdbarchsample.util

import retrofit2.Retrofit

inline fun <reified T> Retrofit.service(): T = create(T::class.java)