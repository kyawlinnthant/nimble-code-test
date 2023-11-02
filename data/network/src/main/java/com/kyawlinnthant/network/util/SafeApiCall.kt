package com.kyawlinnthant.network.util

import com.kyawlinnthant.network.model.ErrorResponse
import kotlinx.serialization.json.Json
import retrofit2.Response

inline fun <reified T> safeApiCall(
    apiCall: () -> Response<T>
): DataResult<T> {
    try {
        val response = apiCall()
        // 2x
        if (response.isSuccessful) {
            val body = response.body()
            return DataResult.Success(data = body!!)
        }
        // 4x
        val errorBody = response.errorBody()
        return if (errorBody != null) {
            val errorResponse = try {
                val json = Json { ignoreUnknownKeys = true }
                json.decodeFromString<ErrorResponse>(errorBody.string())
            } catch (e: Exception) {
                null
            }
            if (errorResponse != null) {
                DataResult.Failed(
                    message = errorResponse.errors.firstOrNull()?.detail
                        ?: "ErrorResponse has no data"
                )
            } else {
                DataResult.Failed(message = "ErrorResponse is null")
            }
        } else {
            DataResult.Failed(message = "Error Body is Null")
        }
    } catch (e: Exception) {
        return DataResult.Failed(message = e.localizedMessage ?: "Something's wrong")
    }
}