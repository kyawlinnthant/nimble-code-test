package com.kyawlinnthant.encrypted

import android.content.SharedPreferences
import androidx.core.content.edit
import com.kyawlinnthant.dispatchers.DispatcherModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EncryptedPrefSourceImpl @Inject constructor(
    private val pref: SharedPreferences,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : EncryptedPrefSource {

    companion object {
        const val PREF_NAME = "encrypted.pref"
        const val ACCESS_TOKEN = "com.kyawlinnthant.access.token"
        const val REFRESH_TOKEN = "com.kyawlinnthant.refresh.token"
    }

    override suspend fun saveAccessToken(token: String) {
        withContext(io) {
            pref.edit {
                putString(ACCESS_TOKEN, token)
            }
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        pref.edit {
            putString(REFRESH_TOKEN, token)
        }
    }

    override suspend fun getAccessToken(): String {
        return withContext(io) {
            pref.getString(ACCESS_TOKEN, null) ?: ""
        }
    }

    override suspend fun getRefreshToken(): String {
        return withContext(io) {
            pref.getString(REFRESH_TOKEN, null) ?: ""
        }
    }
}
