package com.kyawlinnthant.nimble.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kyawlinnthant.navigation.Routes
import com.kyawlinnthant.presentation.graph.authGraph
import com.kyawlinnthant.presentation.graph.homeGraph

@Composable
fun NimbleGraph(
    isLoggedIn: Boolean,
    controller: NavHostController,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val startDestination = if (isLoggedIn) Routes.HOME else Routes.AUTH
    NavHost(
        navController = controller,
        startDestination = startDestination,
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        route = Routes.ROOT
    ) {

        authGraph(controller)
        homeGraph(controller)
    }
}