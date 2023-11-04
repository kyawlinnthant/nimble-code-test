package com.kyawlinnthant.domain

import com.kyawlinnthant.data.model.SurveyAttribute
import com.kyawlinnthant.data.model.SurveysData
import com.kyawlinnthant.data.model.SurveysResponse
import com.kyawlinnthant.data.repository.HomeApiRepository
import com.kyawlinnthant.domain.vo.toVo
import com.kyawlinnthant.network.util.DataResult

class FakeHomeApiRepository : HomeApiRepository {

    companion object {
        val successResponse = SurveysResponse(
            data = listOf(
                SurveysData(
                    id = "id",
                    type = "type",
                    attributes = SurveyAttribute(
                        title = "title",
                        description = "description",
                        aboveThreshold = null,
                        belowThreshold = null,
                        isActive = false,
                        imageUrl = "imageUrl",
                        createdAt = "createAt",
                        activeAt = null,
                        surveyType = "surveyType"
                    )
                )
            )
        )
        private val vo = successResponse.data.map { it.toVo() }
        val entity = vo.map { it.toEntity() }
    }

    override suspend fun getSurveys(
        pageNumber: Int,
        pageSize: Int
    ): DataResult<SurveysResponse> {
        return DataResult.Success(successResponse)
    }
}