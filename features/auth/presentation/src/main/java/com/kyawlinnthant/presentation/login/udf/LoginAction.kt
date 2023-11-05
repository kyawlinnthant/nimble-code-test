package com.kyawlinnthant.presentation.login.udf

sealed interface LoginAction {
    data object Login : LoginAction
    data object ForgotPassword : LoginAction
    data class UpdateEmail(val email: String) : LoginAction
    data class UpdatePassword(val password: String) : LoginAction
}
