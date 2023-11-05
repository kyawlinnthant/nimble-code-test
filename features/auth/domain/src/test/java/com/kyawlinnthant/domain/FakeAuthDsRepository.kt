package com.kyawlinnthant.domain

import com.kyawlinnthant.data.repository.AuthDsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthDsRepository : AuthDsRepository {

    private var accessToken = ""
    private var refreshToken = ""
    private var tokenType = ""
    private var isAuthenticated = false
    override suspend fun putAccessToken(token: String) {
        accessToken = token
    }

    override suspend fun putRefreshToken(token: String) {
        refreshToken = token
    }

    override suspend fun putTokenType(type: String) {
        tokenType = type
    }

    override suspend fun putIsAuthenticated(isLoggedIn: Boolean) {
        isAuthenticated = isLoggedIn
    }

    override suspend fun pullIsAuthenticated(): Flow<Boolean> {
        return flow { emit(isAuthenticated) }
    }

    override suspend fun pullAccessToken(): Flow<String> {
        return flow { emit(accessToken) }
    }

    override suspend fun pullRefreshToken(): Flow<String> {
        return flow { emit(refreshToken) }
    }

    override suspend fun pullTokenType(): Flow<String> {
        return flow { emit(tokenType) }
    }
}
