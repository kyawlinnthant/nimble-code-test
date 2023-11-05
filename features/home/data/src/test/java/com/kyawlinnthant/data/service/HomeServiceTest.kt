package com.kyawlinnthant.data.service

import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.net.HttpURLConnection

class HomeServiceTest {
    private lateinit var service: HomeService
    private lateinit var mockWebServer: MockWebServer

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
    private val factory = json.asConverterFactory("application/json".toMediaType())

    private val requestPageNumber = 5

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(factory)
            .build().create(HomeService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val mockResponse = MockResponse()
        val source = inputStream.source().buffer()
        mockWebServer.enqueue(
            mockResponse.setBody(source.readString(Charsets.UTF_8))
        )
    }

    @Test
    fun `get surveys return success with 200`() = runTest {
        enqueueResponse("success_200.json")
        val response = service.getSurveys(
            pageSize = 1,
            pageNumber = requestPageNumber
        )
        val request = mockWebServer.takeRequest()
        // is correct request
        assertThat(request.method).isEqualTo("GET")
        assertThat(request.path).isEqualTo("/" + HomeService.SURVEYS + "?page%5Bnumber%5D=5&page%5Bsize%5D=1")
        // is correct response
        assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_OK)
        assertThat(response.body()!!.data.size).isEqualTo(requestPageNumber)
    }

    @Test
    fun `get surveys - 4xx, 5xx`() = runTest {
        val expectedResponse = MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        mockWebServer.enqueue(expectedResponse)
        val actual = service.getSurveys(
            pageSize = 1,
            pageNumber = 5
        )
        assertThat(actual.code()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND)
    }

    @Test(expected = Exception::class)
    fun `malformed json throws exception`() = runTest {
        enqueueResponse("malformed.json")
        service.getSurveys(
            pageSize = 1,
            pageNumber = 5
        )
    }
}
