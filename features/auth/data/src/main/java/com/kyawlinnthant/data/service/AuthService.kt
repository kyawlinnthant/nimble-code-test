package com.kyawlinnthant.data.service

import com.kyawlinnthant.data.model.LoginRequest
import com.kyawlinnthant.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    companion object {
        const val LOGIN = "oauth/token"
        private const val FORGOT_PASSWORD = "passwords"
    }

    @POST(LOGIN)
    suspend fun login(
        @Body body: LoginRequest
    ): Response<LoginResponse>
}
