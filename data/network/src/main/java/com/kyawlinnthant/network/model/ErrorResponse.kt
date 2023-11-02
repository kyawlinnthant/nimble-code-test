package com.kyawlinnthant.network.model

import kotlinx.serialization.Serializable

/*{
    "errors": [
    {
        "detail": "Your email or password is incorrect. Please try again.",
        "code": "invalid_email_or_password"
    }
    ]
}*/
@Serializable
data class ErrorResponse(
    val errors: List<ErrorData>
)

@Serializable
data class ErrorData(
    val detail: String,
    val code: String
)