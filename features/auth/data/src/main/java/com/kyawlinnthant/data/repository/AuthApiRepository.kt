package com.kyawlinnthant.data.repository

import com.kyawlinnthant.data.model.ForgotPasswordResponse
import com.kyawlinnthant.data.model.LoginRequest
import com.kyawlinnthant.data.model.LoginResponse
import com.kyawlinnthant.network.util.DataResult

interface AuthApiRepository {
    suspend fun login(
        body: LoginRequest
    ): DataResult<LoginResponse>

    suspend fun forgotPassword(
        email: String
    ): DataResult<ForgotPasswordResponse>
}
