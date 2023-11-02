package com.kyawlinnthant.domain.vo

import com.kyawlinnthant.data.model.SurveysData

data class SurveysVo(
    val id: String,
    val name: String,
    val description: String,
    val image: String
)

fun SurveysData.toVo() = SurveysVo(
    id = this.id,
    name = this.attributes.title,
    description = this.attributes.description,
    image = this.attributes.imageUrl
)
