package com.kyawlinnthant.presentation.password.udf

sealed interface PasswordAction{
    data object Back : PasswordAction
}