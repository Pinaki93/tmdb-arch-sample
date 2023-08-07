package dev.pinaki.arch

interface Mapper<in I, out O> {
    fun map(input: I): O
}