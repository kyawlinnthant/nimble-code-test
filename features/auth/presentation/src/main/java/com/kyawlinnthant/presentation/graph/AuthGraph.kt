package com.kyawlinnthant.presentation.graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kyawlinnthant.navigation.Routes
import com.kyawlinnthant.navigation.Screens
import com.kyawlinnthant.presentation.login.LoginScreen
import com.kyawlinnthant.presentation.login.LoginViewModel
import com.kyawlinnthant.presentation.password.PasswordScreen
import com.kyawlinnthant.presentation.password.PasswordViewModel

fun NavGraphBuilder.authGraph(
    controller: NavHostController
) {
    navigation(
        startDestination = Screens.Login.route,
        route = Routes.AUTH
    ) {
        composable(route = Screens.Login.route) {
            val vm: LoginViewModel = hiltViewModel()
            val form = vm.form.collectAsState()
            val error = vm.error.collectAsState()
            val isLoading = vm.isLoading.collectAsState()
            LoginScreen(
                form = form.value,
                error = error.value,
                onAction = vm::onAction,
                uiEvent = vm.uiEvent,
                isLoading = isLoading.value
            )
        }
        composable(route = Screens.ForgotPassword.getAbsolutePath()) {
            LaunchedEffect(key1 = true) {
                val email = it.arguments?.getString(Screens.EMAIL)
                controller.previousBackStackEntry?.savedStateHandle.apply {
                    Screens.EMAIL to email
                }
            }
            val vm: PasswordViewModel = hiltViewModel()
            val email = vm.email.collectAsState()
            PasswordScreen(
                email = email.value,
                onAction = vm::onAction
            )
        }
    }
}