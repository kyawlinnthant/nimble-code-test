package com.kyawlinnthant.presentation.password.udf

data class PasswordViewModelState(
    val email: String = ""
) {
    fun asEmail() = email
}
