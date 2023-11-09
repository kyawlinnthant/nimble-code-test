package com.kyawlinnthant.data.repository

import kotlinx.coroutines.flow.Flow

interface AuthDsRepository {

    suspend fun putAccessToken(token: String)
    suspend fun putRefreshToken(token: String)
    suspend fun putTokenType(type: String)

    suspend fun putIsAuthenticated(isLoggedIn: Boolean)
    suspend fun pullIsAuthenticated(): Flow<Boolean>

    suspend fun pullAccessToken(): String
    suspend fun pullRefreshToken(): String
    suspend fun pullTokenType(): Flow<String>

    suspend fun alertLogout()
}
