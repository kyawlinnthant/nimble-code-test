package com.kyawlinnthant.nimble.main

data class MainViewModelState(
    val isAdyLoggedIn: Boolean? = null,
    val hasLoggedOut: String = "",
) {
    fun asLoggedIn() = isAdyLoggedIn
    fun asLoggedOut() = hasLoggedOut
}
