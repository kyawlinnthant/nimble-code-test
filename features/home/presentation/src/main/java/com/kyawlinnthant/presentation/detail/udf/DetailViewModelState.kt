package com.kyawlinnthant.presentation.detail.udf

data class DetailViewModelState(
    val id: String = "",
    val name : String = ""
) {
    fun asId() = id
    fun asName() = name
}
