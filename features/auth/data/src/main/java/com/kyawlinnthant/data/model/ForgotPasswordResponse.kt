package com.kyawlinnthant.data.model

import kotlinx.serialization.Serializable

/*{
    "meta": {
    "message": "If your email address exists in our database, you will receive a password recovery link at your email address in a few minutes."
            }
}*/
@Serializable
data class ForgotPasswordResponse(
    val meta: Meta
)

@Serializable
data class Meta(
    val message: String
)
