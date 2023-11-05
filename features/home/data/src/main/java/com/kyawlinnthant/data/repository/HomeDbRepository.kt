package com.kyawlinnthant.data.repository

import com.kyawlinnthant.database.entity.SurveyEntity
import kotlinx.coroutines.flow.Flow

interface HomeDbRepository {
    suspend fun saveSurveys(surveys: List<SurveyEntity>)
    suspend fun getSurveys(): Flow<List<SurveyEntity>>
}
