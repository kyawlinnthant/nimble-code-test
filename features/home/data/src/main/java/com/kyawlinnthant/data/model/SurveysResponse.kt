package com.kyawlinnthant.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SurveysResponse(
    val data: List<SurveysData>
)

@Serializable
data class SurveysData(
    val id: String,
    val type: String,
    val attributes: SurveyAttribute
)

@Serializable
data class SurveyAttribute(
    val title: String,
    val description: String,
    @SerialName("thank_email_above_threshold")
    val aboveThreshold: String?,
    @SerialName("thank_email_below_threshold")
    val belowThreshold: String?,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("cover_image_url")
    val imageUrl: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("active_at")
    val activeAt: String?,
    @SerialName("survey_type")
    val surveyType: String
)
