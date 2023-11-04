package com.kyawlinnthant.network.service

import com.google.common.truth.Truth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kyawlinnthant.network.model.RefreshTokenRequest
import com.kyawlinnthant.util.AppConstant
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

class RefreshTokenServiceTest {

    private lateinit var service: RefreshTokenService
    private lateinit var mockWebServer: MockWebServer

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
    private val factory = json.asConverterFactory("application/json".toMediaType())
    private val refreshBody = RefreshTokenRequest(
        type = "password",
        clientId = AppConstant.CLIENT_ID,
        clientSecret = AppConstant.CLIENT_SECRET,
        refreshToken = "refresh"
    )

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(factory)
            .build().create(RefreshTokenService::class.java)
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
    fun `refresh token return success with 200`() = runTest {
        enqueueResponse("success_200.json")
        val response = service.refreshToken(refreshBody)
        val request = mockWebServer.takeRequest()
        //is correct request
        Truth.assertThat(request.method).isEqualTo("POST")
        Truth.assertThat(request.path).isEqualTo("/" + RefreshTokenService.REFRESH)
        //is correct response
        Truth.assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_OK)
        Truth.assertThat(response.body()!!.data.id).isEqualTo("9010")
        Truth.assertThat(response.body()!!.data.type).isEqualTo("token")
        Truth.assertThat(response.body()!!.data.attributes.accessToken)
            .isEqualTo("Jp3g3znWCQV3BiNHn7uzMddw7woiOXUEXfMEnr9AqbA")
        Truth.assertThat(response.body()!!.data.attributes.tokenType).isEqualTo("Bearer")
        Truth.assertThat(response.body()!!.data.attributes.expired).isEqualTo(7200)
        Truth.assertThat(response.body()!!.data.attributes.refreshToken)
            .isEqualTo("oDF3OWtf0gZnShJqVL9zXDlKS3mr-vfiNDfnZYo583s")
        Truth.assertThat(response.body()!!.data.attributes.created).isEqualTo(1699078715)
    }

    @Test
    fun `refresh token - 4xx, 5xx`() = runTest {
        val expectedResponse = MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        mockWebServer.enqueue(expectedResponse)
        val actual = service.refreshToken(refreshBody)
        Truth.assertThat(actual.code()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND)
    }

    @Test(expected = Exception::class)
    fun `malformed json throws exception`() = runTest {
        enqueueResponse("malformed.json")
        service.refreshToken(refreshBody)
    }
}