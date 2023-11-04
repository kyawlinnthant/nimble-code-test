package com.kyawlinnthant.domain

import com.kyawlinnthant.data.repository.HomeDbRepository
import com.kyawlinnthant.database.entity.SurveyEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeHomeDbRepository : HomeDbRepository {

    private var mockSurveys = mutableListOf<SurveyEntity>()
    override suspend fun saveSurveys(surveys: List<SurveyEntity>) {
        mockSurveys.addAll(surveys)
    }

    override suspend fun getSurveys(): Flow<List<SurveyEntity>> {
        return flow { emit(mockSurveys) }
    }
}