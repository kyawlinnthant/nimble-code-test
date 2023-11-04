package com.kyawlinnthant.domain

import com.kyawlinnthant.data.model.ForgotPasswordResponse
import com.kyawlinnthant.data.model.LoginAttribute
import com.kyawlinnthant.data.model.LoginData
import com.kyawlinnthant.data.model.LoginResponse
import com.kyawlinnthant.data.repository.AuthApiRepository
import com.kyawlinnthant.network.util.DataResult

class FakeAuthApiRepository : AuthApiRepository {

    companion object {
        val mockLoginResponse = LoginResponse(
            data = LoginData(
                id = "id",
                type = "type",
                attributes = LoginAttribute(
                    accessToken = "accessToken",
                    refreshToken = "refreshToken",
                    tokenType = "tokenType",
                    expired = 1234,
                    created = 1234L
                )
            )
        )
    }

    override suspend fun login(email: String, password: String): DataResult<LoginResponse> {
        return DataResult.Success(mockLoginResponse)
    }

    override suspend fun forgotPassword(email: String): DataResult<ForgotPasswordResponse> {
        TODO("Not yet implemented")
    }
}