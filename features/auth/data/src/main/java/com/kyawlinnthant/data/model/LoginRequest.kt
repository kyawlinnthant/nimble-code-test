package com.kyawlinnthant.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
{
  "grant_type": "password",
  "email": "dev@example.com",
  "password": "password",
  "client_id": "ly1nj6n11vionaie65emwzk575hnnmrk",
  "client_secret": "hOzsTeFlT6ko0dme22uGbQal04SBPYc1"
}
*/
@Serializable
data class LoginRequest(
    @SerialName("grant_type")
    val type: String = "password",
    val email: String,
    val password: String,
    @SerialName("client_id")
    val clientId: String,
    @SerialName("client_secret")
    val clientSecret: String
)

