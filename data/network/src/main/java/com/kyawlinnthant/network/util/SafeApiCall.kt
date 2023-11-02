package com.kyawlinnthant.network.util

import retrofit2.Response

inline fun <reified T> safeApiCall(
    apiCall: () ->Response<T>
) : DataResult<T>{
    try {
        val response = apiCall()
        // 200..300
        if (response.isSuccessful) {
            val body = response.body()
            return DataResult.Success(data = body!!)
        }
        // 400..500
        val errorBody = response.errorBody()
        return DataResult.Failed(message = response.message())
    } catch (e: Exception) {
        return DataResult.Failed(message = e.localizedMessage?:"Something's wrong")
    }
}