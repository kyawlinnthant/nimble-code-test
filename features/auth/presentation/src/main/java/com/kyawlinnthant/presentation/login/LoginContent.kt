package com.kyawlinnthant.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.kyawlinnthant.auth.presentation.R
import com.kyawlinnthant.domain.form.LoginError
import com.kyawlinnthant.domain.form.LoginForm
import com.kyawlinnthant.presentation.login.udf.LoginAction
import com.kyawlinnthant.theme.dimen

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
            .padding(paddingValues)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.colorScheme.surface
                    )
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTextField(
            placeholder = stringResource(id = R.string.email),
            value = form.email,
            keyboardType = KeyboardType.Email,
            onValueCleared = {
                onAction(LoginAction.UpdateEmail(""))
            },
            onValueChanged = {
                onAction(LoginAction.UpdateEmail(it))
            },
            isError = error.isErrorEmail,
            errorMessage = stringResource(id = R.string.valid_email)
        )
        Spacer(modifier = modifier.height(MaterialTheme.dimen.base2x))

        PasswordTextField(
            password = form.password,
            isError = error.isErrorPassword,
            errorMessage = stringResource(id = R.string.valid_password),
            placeholder = stringResource(id = R.string.password),
            innerButtonText = stringResource(id = R.string.forgot),
            innerButtonClick = {},
            onValueCleared = {
                onAction(LoginAction.UpdatePassword(""))
            },
            onValueChanged = { onAction(LoginAction.UpdatePassword(it)) }
        )
        Spacer(modifier = modifier.height(MaterialTheme.dimen.base2x))
        Button(
            onClick = { onAction(LoginAction.Login) },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimen.base3x),
            shape = RoundedCornerShape(MaterialTheme.dimen.base)
        ) {
            Text(stringResource(id = R.string.login))
        }
    }
}
