package com.kyawlinnthant.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*{
    "data": {
    "id": 171,
    "type": "token",
    "attributes": {
    "access_token": "6cmbWFycYr9GK5CZ5gRL0aFKZHkUADstNvty-jMqzrE",
    "token_type": "Bearer",
    "expires_in": 7200,
    "refresh_token": "oiL7neqlake97_PEbdYYGX_jKP_R8yv1JdojAQCzWus",
    "created_at": 1600940029
}
}
}*/
@Serializable
data class RefreshTokenResponse(
    val data: RefreshTokenData
)

@Serializable
data class RefreshTokenData(
    val id: Int,
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