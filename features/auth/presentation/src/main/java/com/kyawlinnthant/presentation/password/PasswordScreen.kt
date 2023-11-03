package com.kyawlinnthant.presentation.password

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.kyawlinnthant.auth.presentation.R
import com.kyawlinnthant.presentation.password.udf.PasswordAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordScreen(
    email: String,
    onAction: (PasswordAction) -> Unit,
) {
    val snackHost = remember { SnackbarHostState() }
    Scaffold(
        contentWindowInsets = WindowInsets(
            bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
        ),
        snackbarHost = {
            SnackbarHost(hostState = snackHost)
        },
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.forgot_password)) },
                navigationIcon = {
                    IconButton(onClick = { onAction(PasswordAction.Back) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) {
        PasswordContent(
            paddingValues = it,
            email = email
        )
    }
}