package com.kyawlinnthant.domain.usecase

import com.google.common.truth.Truth
import com.kyawlinnthant.domain.FakeAuthApiRepository
import com.kyawlinnthant.domain.FakeAuthDsRepository
import com.kyawlinnthant.domain.form.LoginForm
import com.kyawlinnthant.network.util.DataResult
import com.kyawlinnthant.testrule.CoroutinesTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginTest {
    @get:Rule
    val testRule = CoroutinesTestRule()

    @Test
    fun `login successfully save required data in pref`() = runTest {
        val api = FakeAuthApiRepository()
        val pref = FakeAuthDsRepository()
        val login = Login(
            api = api,
            ds = pref
        )
        Dispatchers.setMain(StandardTestDispatcher())
        val expected = login.invoke(
            form = LoginForm(
                email = "kyawlinnthant@gmail.com",
                password = "Apple!123"
            )
        )
        val savedAuthenticated = pref.pullIsAuthenticated().first()
        val savedAccessToken = pref.pullAccessToken().first()
        val savedRefreshToken = pref.pullRefreshToken().first()
        val savedTokenType = pref.pullTokenType().first()
        advanceUntilIdle()
        Truth.assertThat(savedAuthenticated).isEqualTo(true)
        Truth.assertThat(savedAccessToken)
            .isEqualTo(FakeAuthApiRepository.mockLoginResponse.data.attributes.accessToken)
        Truth.assertThat(savedRefreshToken)
            .isEqualTo(FakeAuthApiRepository.mockLoginResponse.data.attributes.refreshToken)
        Truth.assertThat(savedTokenType)
            .isEqualTo(FakeAuthApiRepository.mockLoginResponse.data.attributes.tokenType)
        Truth.assertThat(expected).isEqualTo(DataResult.Success(true))
    }
}
