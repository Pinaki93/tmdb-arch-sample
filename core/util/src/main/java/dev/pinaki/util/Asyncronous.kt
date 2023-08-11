package dev.pinaki.util

import dev.pinaki.arch.Async
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * A utility to convert most of the "single" async calls to a [Flow] of [Async].
 * This should be called from the model side of things (usecase/repository).
 */
inline fun <T> asyncFlow(
    coroutineContext: CoroutineContext,
    crossinline block: suspend () -> T,
): Flow<Async<T>> = flow {
    emit(Async.Loading)
    try {
        emit(Async.Success(block()))
    } catch (e: Exception) {
        emit(Async.Error(e))
    }
}.flowOn(coroutineContext)