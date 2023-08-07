package dev.pinaki.arch

interface WorkerMapper<in I, out O> {
    suspend fun map(input: I): O
}