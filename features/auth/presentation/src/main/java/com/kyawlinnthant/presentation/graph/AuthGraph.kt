package com.kyawlinnthant.presentation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kyawlinnthant.navigation.Routes
import com.kyawlinnthant.navigation.Screens

fun NavGraphBuilder.authGraph(
    controller: NavHostController
) {
    navigation(
        startDestination = Screens.Login.route,
        route = Routes.AUTH
    ) {
        composable(route = Screens.Login.route) {

        }
        composable(route = Screens.ForgotPassword.route) {

        }
    }
}