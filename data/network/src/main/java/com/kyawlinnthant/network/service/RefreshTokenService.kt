package com.kyawlinnthant.network.service

import com.kyawlinnthant.network.model.RefreshTokenRequest
import com.kyawlinnthant.network.model.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenService {
    companion object {
        const val REFRESH = "oauth/token"
    }

    @POST(REFRESH)
    suspend fun refreshToken(
        @Body body: RefreshTokenRequest
    ): Response<RefreshTokenResponse>
}