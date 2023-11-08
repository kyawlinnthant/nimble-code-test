package com.kyawlinnthant.data.repository

import com.google.common.truth.Truth.assertThat
import com.kyawlinnthant.data.model.LoginAttribute
import com.kyawlinnthant.data.model.LoginData
import com.kyawlinnthant.data.model.LoginRequest
import com.kyawlinnthant.data.model.LoginResponse
import com.kyawlinnthant.data.service.AuthService
import com.kyawlinnthant.network.util.DataResult
import com.kyawlinnthant.testrule.CoroutinesTestRule
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.Response

class AuthApiRepositoryTest {

    private lateinit var service: AuthService
    private var repository: AuthApiRepositoryImpl? = null

    private val loginBody = LoginRequest(
        type = "password",
        email = "kyawlinnthantkyawlinnthant@gmail.com",
        password = "Apple!123",
        clientId = "BuildConfig.CLIENT_ID",
        clientSecret = "BuildConfig.CLIENT_SECRET"
    )
    private val mockLoginResponse = LoginResponse(
        data = LoginData(
            id = "id",
            type = "type",
            attributes = LoginAttribute(
                accessToken = "accessToken",
                refreshToken = "refreshToken",
                tokenType = "tokenType",
                expired = 1000,
                created = 1000L
            )
        )
    )
    private val error400Code = 400

    @get:Rule
    val testRule = CoroutinesTestRule()

    @Before
    fun setup() {
        service = mock(AuthService::class.java)

        repository = AuthApiRepositoryImpl(
            api = service,
            io = testRule.testDispatcher
        )
    }

    @After
    fun teardown() {
        repository = null
    }

    @Test
    fun `login successfully transform data`() = runTest {
        // assume with mock
        `when`(service.login(loginBody)).thenReturn(Response.success(mockLoginResponse))
        val actual = repository!!.login(
            body = loginBody
        )
        assertThat(actual as DataResult.Success).isEqualTo(DataResult.Success(mockLoginResponse))
    }

    @Test
    fun `login got error and transform result error`() = runTest {
        `when`(service.login(loginBody)).thenReturn(
            Response.error(
                error400Code,
                "error response".toResponseBody("txt".toMediaTypeOrNull())
            )
        )
        val actual = repository!!.login(
            body = loginBody
        )
        assertThat(actual as DataResult.Failed).isEqualTo(DataResult.Failed("ErrorResponse is null"))
    }
}
