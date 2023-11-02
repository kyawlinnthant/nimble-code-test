package com.kyawlinnthant.presentation.login.udf

sealed interface LoginEvent{
    data class ShowSnack(val message : String) : LoginEvent
}