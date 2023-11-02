package com.kyawlinnthant.domain.form

data class LoginError(
    val isErrorEmail : Boolean = false,
    val isErrorPassword : Boolean = false
)