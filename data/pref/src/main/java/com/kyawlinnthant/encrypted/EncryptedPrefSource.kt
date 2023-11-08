package com.kyawlinnthant.encrypted

interface EncryptedPrefSource {

    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun getAccessToken(): String
    suspend fun getRefreshToken(): String
}
