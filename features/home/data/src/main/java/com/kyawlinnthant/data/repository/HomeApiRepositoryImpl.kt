package com.kyawlinnthant.data.repository

import com.kyawlinnthant.data.model.SurveysResponse
import com.kyawlinnthant.data.service.HomeService
import com.kyawlinnthant.dispatchers.DispatcherModule
import com.kyawlinnthant.network.util.DataResult
import com.kyawlinnthant.network.util.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeApiRepositoryImpl @Inject constructor(
    private val api: HomeService,
    @DispatcherModule.IoDispatcher private val io: CoroutineDispatcher
) : HomeApiRepository {
    override suspend fun getSurveys(pageNumber: Int, pageSize: Int): DataResult<SurveysResponse> {
        return withContext(io) {
            safeApiCall {
                api.getSurveys(
                    pageNumber = pageNumber,
                    pageSize = pageSize
                )
            }
        }
    }
}