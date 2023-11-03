package com.kyawlinnthant.data.service

import com.kyawlinnthant.data.model.SurveysResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface HomeService {
    companion object {
        private const val SURVEYS = "surveys"
    }

    @GET(SURVEYS)
    suspend fun getSurveys(
        @Query("page[number]") pageNumber: Int,
        @Query("page[size]") pageSize: Int,
    ): Response<SurveysResponse>
}