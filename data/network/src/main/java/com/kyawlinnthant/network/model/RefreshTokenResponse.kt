package com.kyawlinnthant.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val data: RefreshTokenData
)

@Serializable
data class RefreshTokenData(
    val id: String,
    val type: String,
    val attributes: Attribute
)

@Serializable
data class Attribute(
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
