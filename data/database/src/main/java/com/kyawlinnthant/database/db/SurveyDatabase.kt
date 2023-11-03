package com.kyawlinnthant.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kyawlinnthant.database.dao.SurveyDao
import com.kyawlinnthant.database.entity.SurveyEntity

@Database(
    entities = [SurveyEntity::class],
    version = 1
)
abstract class SurveyDatabase : RoomDatabase() {
    abstract fun dao(): SurveyDao

    companion object {
        const val DB_NAME = "survey_database"
    }
}