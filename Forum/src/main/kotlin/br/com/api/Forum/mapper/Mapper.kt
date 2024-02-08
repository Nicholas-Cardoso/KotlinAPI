package br.com.api.Forum.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}
