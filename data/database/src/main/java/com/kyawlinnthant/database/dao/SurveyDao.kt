package com.kyawlinnthant.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kyawlinnthant.database.entity.SurveyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SurveyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurvey(survey: SurveyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurveys(surveys: List<SurveyEntity>)

    @Delete(entity = SurveyEntity::class)
    suspend fun deleteSurvey(survey: SurveyEntity)

    @Query("DELETE FROM ${SurveyEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${SurveyEntity.TABLE_NAME}")
    fun readSurveys() : Flow<List<SurveyEntity>>
}