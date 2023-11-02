package com.kyawlinnthant.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kyawlinnthant.domain.form.LoginError
import com.kyawlinnthant.domain.form.LoginForm
import com.kyawlinnthant.presentation.login.udf.LoginAction

@Composable
fun LoginContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    form: LoginForm,
    error: LoginError,
    onAction: (LoginAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = form.email,
            onValueChange = {
                onAction(LoginAction.UpdateEmail(it))
            },
            isError = error.isErrorEmail,

        )
        TextField(
            value = form.password,
            onValueChange = {
                onAction(LoginAction.UpdatePassword(it))
            },
            isError = error.isErrorPassword
        )
        Button(onClick = {
            onAction(LoginAction.Login)
        }) {
            Text("Login")
        }
    }
}