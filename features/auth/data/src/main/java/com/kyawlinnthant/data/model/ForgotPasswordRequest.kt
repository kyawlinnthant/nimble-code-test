package com.kyawlinnthant.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*{
    "user": {
    "email": "example@nimblehq.co"
        },
    "client_id": "ly1nj6n11vionaie65emwzk575hnnmrk",
    "client_secret": "hOzsTeFlT6ko0dme22uGbQal04SBPYc1"
}*/
@Serializable
data class ForgotPasswordRequest(
    val user: User,
    @SerialName("client_id")
    val clientId: String,
    @SerialName("client_secret")
    val clientSecret: String
)

@Serializable
data class User(
    val email: String
)
