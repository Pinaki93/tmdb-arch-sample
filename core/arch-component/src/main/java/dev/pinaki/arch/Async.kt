package dev.pinaki.arch

import java.lang.Exception

sealed class Async<out T> {

    data class Error(val exception: Exception?) : Async<Nothing>()

    data class Success<out T>(val data: T) : Async<T>()
}