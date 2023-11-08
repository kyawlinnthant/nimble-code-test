package com.kyawlinnthant.data.service

import com.google.common.truth.Truth.assertThat
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kyawlinnthant.auth.data.BuildConfig
import com.kyawlinnthant.data.model.LoginRequest
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

class AuthServiceTest {
    private lateinit var service: AuthService
    private lateinit var mockWebServer: MockWebServer

    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
    private val factory = json.asConverterFactory("application/json".toMediaType())
    private val loginBody = LoginRequest(
        type = "password",
        email = "kyawlinnthantkyawlinnthant@gmail.com",
        password = "Apple!123",
        clientId = "BuildConfig.CLIENT_ID",
        clientSecret = "BuildConfig.CLIENT_SECRET"
    )
    private val expectedExpired = 7200
    private val expectedCreated = 1698946679

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(factory)
            .build().create(AuthService::class.java)
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
    fun `login return success with 200`() = runTest {
        enqueueResponse("success_200.json")
        val response = service.login(loginBody)
        val request = mockWebServer.takeRequest()
        // is correct request
        assertThat(request.method).isEqualTo("POST")
        assertThat(request.path).isEqualTo("/" + AuthService.LOGIN)
        // is correct response
        assertThat(response.code()).isEqualTo(HttpURLConnection.HTTP_OK)
        assertThat(response.body()!!.data.id).isEqualTo("8743")
        assertThat(response.body()!!.data.type).isEqualTo("token")
        assertThat(response.body()!!.data.attributes.accessToken).isEqualTo("AKo5_Sjw_wsoRfFky2eMk2wgKd2VSFI0trspWTwroXI")
        assertThat(response.body()!!.data.attributes.tokenType).isEqualTo("Bearer")
        assertThat(response.body()!!.data.attributes.expired).isEqualTo(expectedExpired)
        assertThat(response.body()!!.data.attributes.refreshToken).isEqualTo("MVrPA5u-tjEUykYx6rxEWMLrn6yj9pZAWMU94C3nakk")
        assertThat(response.body()!!.data.attributes.created).isEqualTo(expectedCreated)
    }

    @Test
    fun `login - 4xx, 5xx`() = runTest {
        val expectedResponse = MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        mockWebServer.enqueue(expectedResponse)
        val actual = service.login(loginBody)
        assertThat(actual.code()).isEqualTo(HttpURLConnection.HTTP_NOT_FOUND)
    }

    @Test(expected = Exception::class)
    fun `malformed json throws exception`() = runTest {
        enqueueResponse("malformed.json")
        service.login(loginBody)
    }
}
