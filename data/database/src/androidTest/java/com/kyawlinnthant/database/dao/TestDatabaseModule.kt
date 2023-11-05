package com.kyawlinnthant.database.dao

import android.content.Context
import androidx.room.Room
import com.kyawlinnthant.database.DatabaseModule
import com.kyawlinnthant.database.db.SurveyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideInMemoryDb(
        @ApplicationContext context: Context
    ): SurveyDatabase = Room.inMemoryDatabaseBuilder(
        context,
        SurveyDatabase::class.java
    ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideTestDao(
        db: SurveyDatabase
    ): SurveyDao = db.dao()
}
