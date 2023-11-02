package com.kyawlinnthant.presentation.login

import android.util.Log
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.kyawlinnthant.domain.form.LoginError
import com.kyawlinnthant.domain.form.LoginForm
import com.kyawlinnthant.presentation.login.udf.LoginAction
import com.kyawlinnthant.presentation.login.udf.LoginEvent
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun LoginScreen(
    form: LoginForm,
    error: LoginError,
    onAction: (LoginAction) -> Unit,
    uiEvent: SharedFlow<LoginEvent>
) {
    val snackHost = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        uiEvent.collect {
            when (it) {
                is LoginEvent.ShowSnack -> {
                    Log.d("here.message", it.message)
                    snackHost.showSnackbar(it.message)
                }
            }
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        ),
        snackbarHost = {
            SnackbarHost(hostState = snackHost)
        },
    ) {
        LoginContent(
            paddingValues = it,
            form = form,
            error = error,
            onAction = onAction
        )

    }
}