package com.kyawlinnthant.network.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenService {
    companion object {
        private const val REFRESH = "oauth/token"
    }

    @POST(REFRESH)
    suspend fun refreshToken(
        @Body body: RefreshTokenRequest
    ): Response<RefreshTokenResponse>
}