package com.kyawlinnthant.presentation.login

import com.kyawlinnthant.domain.form.LoginError
import com.kyawlinnthant.domain.form.LoginForm

data class LoginViewModelState(
    val form: LoginForm = LoginForm(),
    val error: LoginError = LoginError(),
) {
    fun asForm() = form
    fun asError() = error
}
