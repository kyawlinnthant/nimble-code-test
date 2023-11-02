package com.kyawlinnthant.network.util

sealed class DataResult<out T>(
    open val data: T? = null

) {
    data class Success<out T>(override val data: T) : DataResult<T>()
    data class Failed(val message: String) : DataResult<Nothing>()
}