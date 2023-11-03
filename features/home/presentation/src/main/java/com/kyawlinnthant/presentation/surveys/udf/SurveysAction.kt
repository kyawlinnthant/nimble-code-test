package com.kyawlinnthant.presentation.surveys.udf

sealed interface SurveysAction {
    data class GoToDetail(val id: String, val name : String) : SurveysAction
    data object Retry : SurveysAction
}