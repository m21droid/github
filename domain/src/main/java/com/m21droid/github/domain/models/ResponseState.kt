package com.m21droid.github.domain.models

sealed class ResponseState<out T> {

    object Loading : ResponseState<Nothing>()

    data class Failure(val throwable: Throwable) : ResponseState<Nothing>()

    data class Success<T>(val data: T) : ResponseState<T>()

}