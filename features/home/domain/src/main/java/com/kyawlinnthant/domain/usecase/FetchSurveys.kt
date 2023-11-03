package com.kyawlinnthant.domain.usecase

import com.kyawlinnthant.data.repository.HomeApiRepository
import com.kyawlinnthant.data.repository.HomeDbRepository
import com.kyawlinnthant.database.entity.SurveyEntity
import com.kyawlinnthant.domain.vo.toVo
import com.kyawlinnthant.network.util.DataResult
import javax.inject.Inject

class FetchSurveys @Inject constructor(
    private val api: HomeApiRepository,
    private val dao: HomeDbRepository,
) {
    private val defaultPageNumber = 1
    private val defaultPageSize = 10
    suspend operator fun invoke(): DataResult<Boolean> {
        return when (val response = api.getSurveys(
            pageNumber = defaultPageNumber,
            pageSize = defaultPageSize
        )) {
            is DataResult.Failed -> DataResult.Failed(message = response.message)
            is DataResult.Success -> {
                val dto = response.data.data
                val vo = dto.map { it.toVo() }
                val entity = vo.map { it.toEntity() }
                saveSurveys(entity)
                DataResult.Success(response.data.data.isNotEmpty())
            }
        }
    }

    private suspend fun saveSurveys(surveys: List<SurveyEntity>) {
        dao.saveSurveys(surveys)
    }
}