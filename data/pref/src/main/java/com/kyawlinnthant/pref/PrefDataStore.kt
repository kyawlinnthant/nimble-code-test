package com.kyawlinnthant.pref

import kotlinx.coroutines.flow.Flow

interface PrefDataStore {
    suspend fun putAccessToken(token: String)
    suspend fun putRefreshToken(token: String)
    suspend fun putTokenType(type: String)

    suspend fun putIsAuthenticated(isLoggedIn: Boolean)

    suspend fun pullAccessToken(): Flow<String>
    suspend fun pullRefreshToken(): Flow<String>
    suspend fun pullTokenType(): Flow<String>

    suspend fun pullIsAuthenticated(): Flow<Boolean>
    suspend fun clear()
}