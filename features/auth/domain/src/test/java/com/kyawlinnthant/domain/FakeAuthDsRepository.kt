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

    override suspend fun pullAccessToken(): String {
        return accessToken
    }

    override suspend fun pullRefreshToken(): String {
        return refreshToken
    }

    override suspend fun pullTokenType(): Flow<String> {
        return flow { emit(tokenType) }
    }

    override suspend fun alertLogout() {
        accessToken = ""
        refreshToken = ""
        tokenType = ""
        isAuthenticated = false
    }
}
