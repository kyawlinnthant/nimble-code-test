package com.kyawlinnthant.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kyawlinnthant.navigation.Routes
import com.kyawlinnthant.navigation.Screens

fun NavGraphBuilder.homeGraph(
    controller: NavHostController
) {
    navigation(
        startDestination = Screens.Surveys.route,
        route = Routes.HOME

    ) {
        composable(route = Screens.Surveys.route) {

        }
        composable(route = Screens.SurveyDetail.route) {

        }
    }
}