package br.com.cps.mail.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}