package com.kyawlinnthant.network.forbidden

import kotlinx.coroutines.channels.Channel

interface LogoutAlert {
    val logoutMessage: Channel<String>
    suspend fun alert(error: String)
}
