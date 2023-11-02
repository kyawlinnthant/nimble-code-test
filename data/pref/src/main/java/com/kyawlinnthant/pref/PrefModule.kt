package com.kyawlinnthant.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.kyawlinnthant.dispatchers.DispatcherModule
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
}