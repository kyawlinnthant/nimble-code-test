package com.kyawlinnthant.database

import android.content.Context
import androidx.room.Room
import com.kyawlinnthant.database.dao.SurveyDao
import com.kyawlinnthant.database.db.SurveyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): SurveyDatabase = Room.databaseBuilder(
        context,
        SurveyDatabase::class.java,
        SurveyDatabase.DB_NAME
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(
        db: SurveyDatabase
    ): SurveyDao = db.dao()
}
