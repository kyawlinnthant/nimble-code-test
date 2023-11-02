package com.kyawlinnthant.network.interceptor

import com.kyawlinnthant.network.model.RefreshTokenRequest
import com.kyawlinnthant.network.service.RefreshTokenService
import com.kyawlinnthant.network.util.DataResult
import com.kyawlinnthant.network.util.safeApiCall
import com.kyawlinnthant.pref.PrefDataStore
import com.kyawlinnthant.util.AppConstant
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val api: RefreshTokenService,
    private val ds: PrefDataStore
) : Authenticator {

    // this authenticate will occur everytime server response UnAuthenticated
    override fun authenticate(route: Route?, response: Response): Request {
        return runBlocking {
            val tokenType = ds.pullTokenType().firstOrNull() ?: ""
            val newAccessToken = async { updateToken() }
            response.request.newBuilder().header(
                "Authorization",
                "$tokenType ${newAccessToken.await()}"
            ).build()
        }
    }

    private suspend fun updateToken(): String {
        val refreshToken = ds.pullRefreshToken().firstOrNull() ?: ""
        var accessToken = ds.pullAccessToken().firstOrNull() ?: ""
        val body = RefreshTokenRequest(
            type = "refresh_token",
            refreshToken = refreshToken,
            clientId = AppConstant.CLIENT_ID,
            clientSecret = AppConstant.CLIENT_SECRET
        )
        when (
            val response = safeApiCall {
                api.refreshToken(body = body)
            }
        ) {
            is DataResult.Failed -> {
                //todo : additional logic to prompt the error
            }

            is DataResult.Success -> {
                ds.apply {
                    putAccessToken(response.data.data.attributes.accessToken)
                    putRefreshToken(response.data.data.attributes.refreshToken)
                }
                accessToken = response.data.data.attributes.accessToken
            }
        }
        return accessToken

    }
}