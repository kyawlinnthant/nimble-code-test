package com.kyawlinnthant.domain.usecase

import com.kyawlinnthant.data.repository.AuthApiRepository
import com.kyawlinnthant.data.repository.AuthDsRepository
import com.kyawlinnthant.domain.form.LoginForm
import com.kyawlinnthant.network.util.DataResult
import javax.inject.Inject

class Login @Inject constructor(
    private val api: AuthApiRepository,
    private val ds: AuthDsRepository
) {
    suspend operator fun invoke(
        form: LoginForm
    ): DataResult<Boolean> {
        return when (
            val response = api.login(
                email = form.email.trim(),
                password = form.password.trim(),

            )
        ) {
            is DataResult.Failed -> DataResult.Failed(message = response.message)
            is DataResult.Success -> {
                ds.apply {
                    putIsAuthenticated(true)
                    putAccessToken(token = response.data.data.attributes.accessToken)
                    putRefreshToken(token = response.data.data.attributes.refreshToken)
                    putTokenType(type = response.data.data.attributes.tokenType)
                }

                DataResult.Success(true)
            }
        }
    }
}