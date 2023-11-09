package com.kyawlinnthant.domain.vo

import com.kyawlinnthant.data.model.SurveysData
import com.kyawlinnthant.database.entity.SurveyEntity

data class SurveysVo(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val createdAt: String,
    val activeAt: String
) {
    fun toEntity() = SurveyEntity(
        id = id,
        name = name,
        description = description,
        image = image,
        createdAt = createdAt,
        activeAt = activeAt
    )
}

fun SurveysData.toVo() = SurveysVo(
    id = this.id,
    name = this.attributes.title,
    description = this.attributes.description,
    image = this.attributes.imageUrl,
    createdAt = this.attributes.createdAt,
    activeAt = this.attributes.activeAt ?: ""
)

fun SurveyEntity.toVo() = SurveysVo(
    id = this.id,
    name = this.name,
    description = this.description,
    image = this.image,
    createdAt = this.createdAt,
    activeAt = this.activeAt
)
