package com.kyawlinnthant.presentation.graph

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kyawlinnthant.navigation.Routes
import com.kyawlinnthant.navigation.Screens
import com.kyawlinnthant.presentation.detail.DetailScreen
import com.kyawlinnthant.presentation.detail.DetailViewModel
import com.kyawlinnthant.presentation.surveys.SurveysScreen
import com.kyawlinnthant.presentation.surveys.SurveysViewModel

fun NavGraphBuilder.homeGraph(
    controller: NavHostController
) {
    navigation(
        startDestination = Screens.Surveys.route,
        route = Routes.HOME

    ) {
        composable(route = Screens.Surveys.route) {
            val vm: SurveysViewModel = hiltViewModel()
            val uiState = vm.uiState.collectAsState()
            SurveysScreen(
                uiState = uiState.value,
                onAction = vm::onAction
            )
        }
        composable(route = Screens.SurveyDetail.getAbsolutePath()) {
            LaunchedEffect(key1 = true) {
                val surveyId = it.arguments?.getString(Screens.DETAIL_ID)
                Log.d("here.graph", "$surveyId")
                controller.previousBackStackEntry?.savedStateHandle.apply {
                    Screens.DETAIL_ID to surveyId
                }
            }
            val vm: DetailViewModel = hiltViewModel()
            val id = vm.surveyId.collectAsState()
            val name = vm.surveyName.collectAsState()
            DetailScreen(
                id = id.value,
                name = name.value,
                onAction = vm::onAction
            )
        }
    }
}
