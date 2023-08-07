package dev.pinaki.util

import dev.pinaki.arch.Async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * A utility to convert most of the "single" async calls to a [Flow] of [Async].
 * This should be called from the model side of things (usecase/repository).
 *
 * While it might be argued that model shouldn't emit loading but this is a necessary evil here.
 * It removes the requirement for glue code
 */
fun <T> runAsync(
    block: suspend () -> T,
): Flow<Async<T>> = flow {
    try {
        emit(Async.Success(block()))
    } catch (e: Exception) {
        e.printStackTrace()
        emit(Async.Error(e))
    }

}