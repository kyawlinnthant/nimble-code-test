package com.kyawlinnthant.domain.usecase

import com.kyawlinnthant.data.repository.HomeDbRepository
import com.kyawlinnthant.domain.vo.SurveysVo
import com.kyawlinnthant.domain.vo.toVo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSurveys @Inject constructor(
    private val dao: HomeDbRepository
) {
    suspend operator fun invoke(): Flow<List<SurveysVo>> {
        return dao.getSurveys().map { surveys ->
            surveys.map {
                it.toVo()
            }
        }
    }
}
