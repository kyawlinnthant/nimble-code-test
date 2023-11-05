package com.kyawlinnthant.data.repository

import com.kyawlinnthant.data.model.SurveysResponse
import com.kyawlinnthant.network.util.DataResult

interface HomeApiRepository {
    suspend fun getSurveys(
        pageNumber: Int,
        pageSize: Int
    ): DataResult<SurveysResponse>
}
