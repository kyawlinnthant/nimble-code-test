package com.kyawlinnthant.presentation.surveys

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.kyawlinnthant.presentation.surveys.udf.SurveysAction
import com.kyawlinnthant.presentation.surveys.udf.SurveysUiState

@Composable
fun SurveysScreen(
    uiState: SurveysUiState,
    onAction: (SurveysAction) -> Unit,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(
            left = 0,
            top = 0,
            right = 0,
            bottom = 0,
        ),
    ) {
        SurveysContent(
            uiState = uiState,
            paddingValues = it,
            onAction = onAction
        )
    }

}