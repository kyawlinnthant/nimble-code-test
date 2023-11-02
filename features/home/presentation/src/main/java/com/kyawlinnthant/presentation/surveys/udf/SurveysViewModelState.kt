package com.kyawlinnthant.presentation.surveys.udf

import com.kyawlinnthant.domain.vo.SurveysVo

data class SurveysViewModelState(
    val isLoading: Boolean = false,
    val error: String = "",
    val surveys: List<SurveysVo> = emptyList()
) {
    fun asUiState(): SurveysUiState = when {
        isLoading && surveys.isEmpty() -> SurveysUiState.FirstTimeLoading
        error.isNotEmpty() && surveys.isEmpty() -> SurveysUiState.FirstTimeError(message = error)
        surveys.isNotEmpty() -> SurveysUiState.HasData(surveys = surveys)
        else -> SurveysUiState.Default
    }
}
