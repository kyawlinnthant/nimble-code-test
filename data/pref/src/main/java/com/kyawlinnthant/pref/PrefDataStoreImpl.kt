package com.kyawlinnthant.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kyawlinnthant.dispatchers.DispatcherModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class PrefDataStoreImpl @Inject constructor(
    private val ds: DataStore<Preferences>,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher

) : PrefDataStore {

    companion object {
        const val PREF_NAME = "token.ds.pref"
        val TOKEN_TYPE = stringPreferencesKey("com.kyawlinnthant.token.type")
        val IS_AUTHENTICATED = booleanPreferencesKey("com.kyawlinnthant.authenticated")
    }

    override suspend fun putTokenType(type: String) {
        withContext(io) {
            ds.edit {
                it[TOKEN_TYPE] = type
            }
        }
    }

    override suspend fun putIsAuthenticated(isLoggedIn: Boolean) {
        withContext(io) {
            ds.edit {
                it[IS_AUTHENTICATED] = isLoggedIn
            }
        }
    }

    override suspend fun pullTokenType(): Flow<String> {
        return ds.data
            .catch { e ->
                if (e is IOException) emit(emptyPreferences()) else throw e
            }
            .map {
                it[TOKEN_TYPE] ?: ""
            }
            .flowOn(io)
    }

    override suspend fun pullIsAuthenticated(): Flow<Boolean> {
        return ds.data
            .catch { e ->
                if (e is IOException) emit(emptyPreferences()) else throw e
            }
            .map {
                it[IS_AUTHENTICATED] ?: false
            }
            .flowOn(io)
    }

    override suspend fun clear() {
        withContext(io) {
            ds.edit { it.clear() }
        }
    }
}
