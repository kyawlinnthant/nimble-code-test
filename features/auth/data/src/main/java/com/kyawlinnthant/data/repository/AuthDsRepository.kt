package com.kyawlinnthant.data.repository

interface AuthDsRepository {

    suspend fun putAccessToken(token: String)
    suspend fun putRefreshToken(token: String)
    suspend fun putTokenType(type: String)
}