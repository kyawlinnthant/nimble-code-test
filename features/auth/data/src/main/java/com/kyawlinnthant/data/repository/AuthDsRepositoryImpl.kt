package com.kyawlinnthant.data.repository

import com.kyawlinnthant.dispatchers.DispatcherModule
import com.kyawlinnthant.pref.PrefDataStore
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthDsRepositoryImpl @Inject constructor(
    private val ds: PrefDataStore,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : AuthDsRepository {
    override suspend fun putAccessToken(token: String) {
        withContext(io) {
            ds.putAccessToken(token)
        }
    }

    override suspend fun putRefreshToken(token: String) {
        withContext(io) {
            ds.putRefreshToken(token)
        }
    }

    override suspend fun putTokenType(type: String) {
        withContext(io) {
            ds.putTokenType(type)
        }
    }

    override suspend fun putIsAuthenticated(isLoggedIn: Boolean) {
        withContext(io) {
            ds.putIsAuthenticated(isLoggedIn)
        }
    }

    override suspend fun pullIsAuthenticated(): Flow<Boolean> {
        return ds.pullIsAuthenticated().flowOn(io)
    }
}