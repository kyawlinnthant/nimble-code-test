package com.kyawlinnthant.pref

import kotlinx.coroutines.flow.Flow

interface PrefDataStore {
    suspend fun putTokenType(type: String)
    suspend fun putIsAuthenticated(isLoggedIn: Boolean)
    suspend fun pullTokenType(): Flow<String>

    suspend fun pullIsAuthenticated(): Flow<Boolean>
    suspend fun clear()
}
