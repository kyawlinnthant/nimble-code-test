package com.kyawlinnthant.presentation.login.udf

import com.kyawlinnthant.domain.form.LoginError
import com.kyawlinnthant.domain.form.LoginForm

data class LoginViewModelState(
    val form: LoginForm = LoginForm(),
    val error: LoginError = LoginError(),
    val isLoading: Boolean = false
) {
    fun asForm() = form
    fun asError() = error
    fun asLoading() = isLoading
}
