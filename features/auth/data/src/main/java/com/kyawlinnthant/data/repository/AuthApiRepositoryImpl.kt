package com.kyawlinnthant.data.repository

import com.kyawlinnthant.data.model.ForgotPasswordResponse
import com.kyawlinnthant.data.model.LoginRequest
import com.kyawlinnthant.data.model.LoginResponse
import com.kyawlinnthant.data.service.AuthService
import com.kyawlinnthant.dispatchers.DispatcherModule
import com.kyawlinnthant.network.util.DataResult
import com.kyawlinnthant.network.util.safeApiCall
import com.kyawlinnthant.util.AppConstant
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthApiRepositoryImpl @Inject constructor(
    private val api: AuthService,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : AuthApiRepository {
    override suspend fun login(email: String, password: String): DataResult<LoginResponse> {
        val body = LoginRequest(
            type = "password",
            email = email,
            password = password,
            clientId = AppConstant.CLIENT_ID,
            clientSecret = AppConstant.CLIENT_SECRET
        )
        return withContext(io) {
            safeApiCall {
                api.login(body = body)
            }
        }
    }

    override suspend fun forgotPassword(email: String): DataResult<ForgotPasswordResponse> {
        TODO("Not yet implemented")
    }
}
