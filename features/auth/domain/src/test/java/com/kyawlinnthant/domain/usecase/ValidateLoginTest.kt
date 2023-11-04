package com.kyawlinnthant.domain.usecase

import com.google.common.truth.Truth
import com.kyawlinnthant.domain.form.LoginError
import com.kyawlinnthant.domain.form.LoginForm
import com.kyawlinnthant.testrule.CoroutinesTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ValidateLoginTest {

    @get:Rule
    val testRule = CoroutinesTestRule()

    @Test
    fun `validate login form successfully done`() = runTest {
        val validator = ValidateLogin()
        val successForm = LoginForm(
            email = "kyawlinnthant@gmail.com",
            password = "Apple!123"
        )
        val invalidEmail = LoginForm(
            email = "kyaw linn thant@gmail.com",
            password = "Apple!123"
        )
        val invalidPassword = LoginForm(
            email = "kyawlinnthant@gmail.com",
            password = "123"
        )
        Dispatchers.setMain(StandardTestDispatcher())
        val successExpected = validator.invoke(form = successForm)
        val invalidEmailExpected = validator.invoke(form = invalidEmail)
        val invalidPasswordExpected = validator.invoke(form = invalidPassword)
        advanceUntilIdle()
        Truth.assertThat(successExpected).isEqualTo(
            LoginError(
                isErrorEmail = false,
                isErrorPassword = false
            )
        )
        Truth.assertThat(invalidEmailExpected).isEqualTo(
            LoginError(
                isErrorEmail = true,
                isErrorPassword = false
            )
        )
        Truth.assertThat(invalidPasswordExpected).isEqualTo(
            LoginError(
                isErrorEmail = false,
                isErrorPassword = true
            )
        )
    }
}