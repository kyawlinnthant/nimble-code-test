package com.kyawlinnthant.data.repository

import com.kyawlinnthant.data.model.ForgotPasswordResponse
import com.kyawlinnthant.data.model.LoginResponse
import com.kyawlinnthant.network.util.DataResult
import javax.inject.Inject

class AuthApiRepositoryImpl @Inject constructor(

) : AuthApiRepository{
    override suspend fun login(email: String, password: String): DataResult<LoginResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword(email: String): DataResult<ForgotPasswordResponse> {
        TODO("Not yet implemented")
    }
}