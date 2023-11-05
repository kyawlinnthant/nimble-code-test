package com.kyawlinnthant.data.repository

import com.kyawlinnthant.database.dao.SurveyDao
import com.kyawlinnthant.database.entity.SurveyEntity
import com.kyawlinnthant.dispatchers.DispatcherModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeDbRepositoryImpl @Inject constructor(
    private val dao: SurveyDao,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : HomeDbRepository {
    override suspend fun saveSurveys(surveys: List<SurveyEntity>) {
        withContext(io) {
            dao.insertSurveys(surveys)
        }
    }

    override suspend fun getSurveys(): Flow<List<SurveyEntity>> {
        return dao.readSurveys().flowOn(io)
    }
}
