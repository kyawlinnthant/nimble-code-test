package com.kyawlinnthant.nimble.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.kyawlinnthant.navigation.NavigationInstructor
import com.kyawlinnthant.nimble.navigation.NimbleGraph

@Composable
fun MainScreen() {
    val vm: MainViewModel = hiltViewModel()
    val navHostController = rememberNavController()
    val isLoggedIn = vm.isLoggedIn.collectAsState()
    val hasLoggedOut = vm.isLoggedOut.collectAsState()
    NavigationInstructor(
        instructor = vm.appNavigator.instructor,
        controller = navHostController,
    )

    if (hasLoggedOut.value.isNotEmpty()) {
        LoginAgainDialog(description = hasLoggedOut.value, onClick = vm::loginAgain)
    }
    Scaffold(
        contentWindowInsets = WindowInsets(
            left = 0,
            top = 0,
            right = 0,
            bottom = 0,
        ),
    ) {
        isLoggedIn.value?.let { status ->
            NimbleGraph(
                paddingValues = it,
                controller = navHostController,
                isLoggedIn = status,
            )
        }
    }
}
