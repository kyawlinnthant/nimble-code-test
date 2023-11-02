package com.kyawlinnthant.presentation.surveys.udf

import com.kyawlinnthant.domain.vo.SurveysVo

sealed interface SurveysUiState{
    data object Default : SurveysUiState
    data object FirstTimeLoading : SurveysUiState
    data class FirstTimeError(val message : String) : SurveysUiState
    data class HasData(val surveys : List<SurveysVo>) : SurveysUiState
}