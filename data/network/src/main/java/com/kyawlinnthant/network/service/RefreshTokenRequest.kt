package com.kyawlinnthant.network.service

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*{
    "grant_type": "refresh_token",
    "refresh_token": "t9KR8HoiJXZfdfs5vVB1PKwjg7m7ynvXBrUkpUyxYkU",
    "client_id": "6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw",
    "client_secret": "_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw"
}*/

@Serializable
data class RefreshTokenRequest(
    @SerialName("grant_type")
    val type: String = "refresh_token",
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("client_id")
    val clientId: String,
    @SerialName("client_secret")
    val clientSecret: String
)
