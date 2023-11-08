package com.kyawlinnthant.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.kyawlinnthant.auth.presentation.R
import com.kyawlinnthant.theme.dimen

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "Placeholder",
    password: String = "",
    onValueChanged: (String) -> Unit,
    onValueCleared: () -> Unit = {},
    innerButtonClick: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Done,
    keyboardAction: (KeyboardActionScope) -> Unit = {},
    isError: Boolean,
    errorMessage: String,
    innerButtonText: String,
    backgroundAlpha: Float = 0.2f
) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    val icon =
        if (passwordVisibility) {
            painterResource(id = R.drawable.hide_password)
        } else {
            painterResource(id = R.drawable.show_password)
        }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.dimen.base3x)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(MaterialTheme.dimen.base))
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = backgroundAlpha)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = modifier.size(MaterialTheme.dimen.base2x))
            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimen.base2x)
                    .weight(1f),
                value = password,
                onValueChange = {
                    onValueChanged(it)
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                decorationBox = { innerTextField ->
                    if (password.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    innerTextField()
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onDone = keyboardAction
                ),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                cursorBrush = SolidColor(
                    value = MaterialTheme.colorScheme.primary
                )
            )

            if (password.isNotEmpty()) {
                IconButton(onClick = onValueCleared) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_clear_24),
                        contentDescription = "Trailing Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant

                    )
                }
            }
            if (password.isNotEmpty()) {
                IconButton(onClick = {
                    passwordVisibility = !passwordVisibility
                }) {
                    Icon(
                        painter = icon,
                        contentDescription = "Close Text",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            if (password.isEmpty()) {
                TextButton(onClick = innerButtonClick) {
                    Text(text = innerButtonText)
                }
            }
        }

        VisibilityAnimator(
            isVisible = isError,
            errorMessage = errorMessage
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun EmptyPasswordTextFieldPreview() {
    Surface {
        PasswordTextField(
            password = "",
            onValueChanged = {},
            isError = false,
            errorMessage = "",
            keyboardAction = {},
            innerButtonText = "Forgot?"
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun NotEmptyPasswordTextFieldPreview() {
    Surface {
        PasswordTextField(
            password = "password",
            onValueChanged = {},
            isError = false,
            errorMessage = "",
            keyboardAction = {},
            innerButtonText = "Forgot?"
        )
    }
}
