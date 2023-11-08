package com.kyawlinnthant

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.kyawlinnthant.dispatchers.DispatcherModule
import com.kyawlinnthant.encrypted.EncryptedPrefSource
import com.kyawlinnthant.encrypted.EncryptedPrefSourceImpl
import com.kyawlinnthant.pref.PrefDataStore
import com.kyawlinnthant.pref.PrefDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PrefModule {
    @Provides
    @Singleton
    fun provideDatastore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = {
                context.preferencesDataStoreFile(PrefDataStoreImpl.PREF_NAME)
            }
        )
    }

    @Provides
    @Singleton
    fun provideTokenPref(
        ds: DataStore<Preferences>,
        @DispatcherModule.IoDispatcher io: CoroutineDispatcher
    ): PrefDataStore {
        return PrefDataStoreImpl(
            ds = ds,
            io = io
        )
    }

    @Provides
    @Singleton
    fun provideMasterKey(
        @ApplicationContext context: Context
    ): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context,
        masterKey: MasterKey
    ): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            EncryptedPrefSourceImpl.PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun provideEncryptedPref(
        pref: SharedPreferences,
        @DispatcherModule.IoDispatcher io: CoroutineDispatcher
    ): EncryptedPrefSource {
        return EncryptedPrefSourceImpl(
            pref = pref,
            io = io
        )
    }
}
