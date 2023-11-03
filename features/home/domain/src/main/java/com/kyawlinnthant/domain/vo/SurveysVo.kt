package com.kyawlinnthant.domain.vo

import com.kyawlinnthant.data.model.SurveysData
import com.kyawlinnthant.database.entity.SurveyEntity

data class SurveysVo(
    val id: String,
    val name: String,
    val description: String,
    val image: String
) {
    fun toEntity() = SurveyEntity(
        id = id,
        name = name,
        description = description,
        image = image
    )
}

fun SurveysData.toVo() = SurveysVo(
    id = this.id,
    name = this.attributes.title,
    description = this.attributes.description,
    image = this.attributes.imageUrl
)

fun SurveyEntity.toVo() = SurveysVo(
    id = this.id,
    name = this.name,
    description = this.description,
    image = this.image
)