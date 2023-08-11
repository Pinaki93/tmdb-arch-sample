package dev.pinaki.arch

import java.lang.Exception

sealed class Async<out T> {

    object Loading : Async<Nothing>()
    data class Error(val exception: Exception?) : Async<Nothing>()

    data class Success<out T>(val data: T) : Async<T>()
}