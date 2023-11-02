package com.kyawlinnthant.data.model

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
    val errors: ErrorData
)

@Serializable
data class ErrorData(
    val detail: String,
    val code: String
)