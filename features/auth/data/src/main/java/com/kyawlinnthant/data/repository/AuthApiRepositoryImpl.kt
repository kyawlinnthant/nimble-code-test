package com.kyawlinnthant.data.repository

import com.kyawlinnthant.data.model.ForgotPasswordResponse
import com.kyawlinnthant.data.model.LoginRequest
import com.kyawlinnthant.data.model.LoginResponse
import com.kyawlinnthant.data.service.AuthService
import com.kyawlinnthant.dispatchers.DispatcherModule
import com.kyawlinnthant.network.util.DataResult
import com.kyawlinnthant.network.util.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthApiRepositoryImpl @Inject constructor(
    private val api: AuthService,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : AuthApiRepository {
    override suspend fun login(
        body: LoginRequest
    ): DataResult<LoginResponse> {
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
