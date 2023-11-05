package com.kyawlinnthant.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.kyawlinnthant.auth.presentation.R
import com.kyawlinnthant.domain.form.LoginError
import com.kyawlinnthant.domain.form.LoginForm
import com.kyawlinnthant.presentation.login.udf.LoginAction
import com.kyawlinnthant.presentation.login.udf.LoginEvent
import com.kyawlinnthant.theme.dimen
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun LoginScreen(
    form: LoginForm,
    error: LoginError,
    onAction: (LoginAction) -> Unit,
    uiEvent: SharedFlow<LoginEvent>,
    isLoading: Boolean
) {
    val snackHost = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        uiEvent.collect {
            when (it) {
                is LoginEvent.ShowSnack -> {
                    snackHost.showSnackbar(it.message)
                }
            }
        }
    }

    if (isLoading) {
        Dialog(onDismissRequest = {}) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(MaterialTheme.dimen.base2x))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(MaterialTheme.dimen.base3x),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.logging))
            }
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets(
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        ),
        snackbarHost = {
            SnackbarHost(hostState = snackHost)
        }
    ) {
        LoginContent(
            paddingValues = it,
            form = form,
            error = error,
            onAction = onAction
        )
    }
}
