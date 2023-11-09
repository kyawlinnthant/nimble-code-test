package com.kyawlinnthant.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.kyawlinnthant.auth.presentation.R
import com.kyawlinnthant.domain.form.LoginError
import com.kyawlinnthant.domain.form.LoginForm
import com.kyawlinnthant.presentation.login.udf.LoginAction
import com.kyawlinnthant.theme.NimbleTheme
import com.kyawlinnthant.theme.dimen
import com.kyawlinnthant.util.AppConstant

@Composable
fun LoginContent(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    form: LoginForm,
    error: LoginError,
    onAction: (LoginAction) -> Unit,
    scrim: Brush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surface.copy(alpha = AppConstant.BACKGROUND_ALPHA1),
            MaterialTheme.colorScheme.surface.copy(alpha = AppConstant.BACKGROUND_ALPHA2),
            MaterialTheme.colorScheme.surface.copy(alpha = AppConstant.BACKGROUND_ALPHA3),
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.surface,
            MaterialTheme.colorScheme.surface
        ),
        tileMode = TileMode.Decal
    ),
    logoWeight: Float = 0.4f,
    inputWeight: Float = 0.6f,
    buttonWeight: Int = 800
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .drawBehind {
                    drawRect(
                        brush = scrim
                    )
                }
        )
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(logoWeight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo_white),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .weight(inputWeight),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginTextField(
                    placeholder = stringResource(id = R.string.email),
                    value = form.email,
                    keyboardType = KeyboardType.Email,
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
                    onValueChanged = { onAction(LoginAction.UpdatePassword(it)) }
                )
                Spacer(modifier = modifier.height(MaterialTheme.dimen.base2x))
                Button(
                    onClick = { onAction(LoginAction.Login) },
                    modifier = modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.dimen.button)
                        .padding(horizontal = MaterialTheme.dimen.base3x),
                    shape = RoundedCornerShape(MaterialTheme.dimen.base),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface,
                        contentColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight(buttonWeight)
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun LightPreview() {
    NimbleTheme {
        Surface {
            LoginContent(
                paddingValues = PaddingValues(),
                form = LoginForm(
                    email = "",
                    password = ""
                ),
                error = LoginError(
                    isErrorEmail = false,
                    isErrorPassword = false
                ),
                onAction = {}
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkPreview() {
    NimbleTheme {
        Surface {
            LoginContent(
                paddingValues = PaddingValues(),
                form = LoginForm(
                    email = "",
                    password = ""
                ),
                error = LoginError(
                    isErrorEmail = false,
                    isErrorPassword = false
                ),
                onAction = {}
            )
        }
    }
}
