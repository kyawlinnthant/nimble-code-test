package com.kyawlinnthant.data.repository

import com.kyawlinnthant.pref.PrefDataStore
import javax.inject.Inject

class AuthDsRepositoryImpl @Inject constructor(
    private val ds : PrefDataStore,
) : AuthDsRepository{
    override suspend fun putAccessToken(token: String) {
        ds.putAccessToken(token)
    }

    override suspend fun putRefreshToken(token: String) {
        ds.putRefreshToken(token)
    }

    override suspend fun putTokenType(type: String) {
        ds.putTokenType(type)
    }
}