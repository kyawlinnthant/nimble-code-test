package com.kyawlinnthant.presentation.login

sealed interface LoginAction{
    data object Login : LoginAction
}