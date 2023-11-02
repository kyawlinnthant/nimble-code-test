package com.kyawlinnthant.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
200
{
  "data": {
    "id": "123",
    "type": "token",
    "attributes": {
      "access_token": "123-f2i0CG6MDsf-wJE9FyYrhSGAOtxBkhYWDI",
      "token_type": "Bearer",
      "expires_in": 7200,
      "refresh_token": "l27GNT0kmkPbnEaUxniXyu4cHfPyWFr00kZTX5oWKA6c",
      "created_at": 1681974651
    }
  }
}

400
{
    "errors": [
        {
            "detail": "Your email or password is incorrect. Please try again.",
            "code": "invalid_email_or_password"
        }
    ]
}

401
{
    "errors": [
        {
            "detail": "Client authentication failed due to unknown client, no client authentication included, or unsupported authentication method.",
            "code": "invalid_client"
        }
    ]
}
*/
@Serializable
data class LoginResponse(
    val data : LoginData
)

@Serializable
data class LoginData(
    val id : String,
    val type : String,
    val attributes : LoginAttribute
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
    val created : Long
)

