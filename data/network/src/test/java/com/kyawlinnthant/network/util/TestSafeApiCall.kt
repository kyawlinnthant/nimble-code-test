package com.kyawlinnthant.network.util

import com.google.common.truth.Truth.assertThat
import com.kyawlinnthant.network.model.ErrorData
import com.kyawlinnthant.network.model.ErrorResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class TestSafeApiCall {

    private val json = Json { ignoreUnknownKeys = true }
    private val mediaType: MediaType? = "application/json".toMediaTypeOrNull()
    private val errorResponse = ErrorResponse(
        errors = listOf(
            ErrorData(
                detail = "Detail",
                code = "code"
            )
        )
    )
    private val emptyErrorResponse = ErrorResponse(
        errors = emptyList()
    )

    private val errorResponseJson = json.encodeToString(ErrorResponse.serializer(), errorResponse)
    private val emptyErrorResponseJson = json.encodeToString(ErrorResponse.serializer(), emptyErrorResponse)
    private val error400 : ResponseBody = errorResponseJson.toResponseBody(mediaType)
    private val emptyError : ResponseBody = emptyErrorResponseJson.toResponseBody(mediaType)
    private val nullError : ResponseBody = "".toResponseBody(mediaType)


    private fun successfulCall() = Response.success(true)
    private fun errorCall() = Response.error<ErrorResponse>(400,error400)
    private fun emptyErrorCall() = Response.error<ErrorResponse>(400,emptyError)
    private fun nullErrorCall() = Response.error<ErrorResponse>(400,nullError)
    @Test
    fun `2xx successful apiCall return Success`() {
        val expected = safeApiCall {
            successfulCall()
        }
        assertThat(expected).isEqualTo(DataResult.Success(true))
    }

    @Test
    fun `4xx 5xx apiCall return Failed`() {
        val expected = safeApiCall {
            errorCall()
        }
        assertThat(expected).isEqualTo(DataResult.Failed(message = "Detail"))
    }

    @Test
    fun `error apiCall with empty errorBody return Error ErrorResponse has no data`() {
        val expected = safeApiCall {
            emptyErrorCall()
        }
        assertThat(expected).isEqualTo(DataResult.Failed(message = "ErrorResponse has no data"))
    }

    @Test
    fun `null error apiCall with null errorBody return ErrorResponse is null`() {
        val expected = safeApiCall {
            nullErrorCall()
        }
        assertThat(expected).isEqualTo(DataResult.Failed(message = "ErrorResponse is null"))
    }
}