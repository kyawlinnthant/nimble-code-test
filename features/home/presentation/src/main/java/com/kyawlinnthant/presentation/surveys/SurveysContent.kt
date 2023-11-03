package com.kyawlinnthant.presentation.surveys

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kyawlinnthant.presentation.surveys.udf.SurveysAction
import com.kyawlinnthant.presentation.surveys.udf.SurveysUiState
import com.kyawlinnthant.presentation.surveys.view.DefaultView
import com.kyawlinnthant.presentation.surveys.view.FirstTimeErrorView
import com.kyawlinnthant.presentation.surveys.view.FirstTimeLoadingView
import com.kyawlinnthant.presentation.surveys.view.HasDataView

@Composable
fun SurveysContent(
    modifier: Modifier = Modifier,
    uiState: SurveysUiState,
    paddingValues: PaddingValues,
    onAction: (SurveysAction) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        when (uiState) {
            SurveysUiState.Default -> {
                DefaultView()
            }

            is SurveysUiState.FirstTimeError -> {
                FirstTimeErrorView(
                    onRetry = { onAction(SurveysAction.Retry) },
                    error = uiState.message
                )
            }

            SurveysUiState.FirstTimeLoading -> {
                FirstTimeLoadingView()
            }

            is SurveysUiState.HasData -> {
                HasDataView(
                    surveys = uiState.surveys,
                    onAction = onAction
                )
            }
        }
    }
}