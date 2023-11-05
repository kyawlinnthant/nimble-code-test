package com.kyawlinnthant.presentation.detail.udf

sealed interface DetailAction {
    data object Back : DetailAction
}
