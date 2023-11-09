package com.kyawlinnthant.network.forbidden

import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class LogoutAlertImpl @Inject constructor() : LogoutAlert {

    private val message = Channel<String>()
    override val logoutMessage: Channel<String>
        get() = message

    override suspend fun alert(error: String) {
        message.send(error)
    }
}
