package com.kyawlinnthant.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val data: LoginData
)

@Serializable
data class LoginData(
    val id: String,
    val type: String,
    val attributes: LoginAttribute
)

@Serializable
data class LoginAttribute(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("expires_in")
    val expired: Int,
    @SerialName("created_at")
    val created: Long
)
