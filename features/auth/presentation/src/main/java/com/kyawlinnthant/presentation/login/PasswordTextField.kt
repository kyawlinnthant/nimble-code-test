package com.kyawlinnthant.presentation.login

import android.content.res.Configuration
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.kyawlinnthant.theme.NimbleTheme
import com.kyawlinnthant.theme.dimen

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "Placeholder",
    password: String = "",
    onValueChanged: (String) -> Unit,
    innerButtonClick: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Done,
    keyboardAction: (KeyboardActionScope) -> Unit = {},
    isError: Boolean,
    errorMessage: String,
    innerButtonText: String,
    backgroundAlpha: Float = 0.18f,
    placeholderAlpha: Float = 0.5f
) {
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
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                decorationBox = { innerTextField ->
                    if (password.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = placeholderAlpha),
                            style = MaterialTheme.typography.bodyMedium
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
                visualTransformation = PasswordVisualTransformation(),
                cursorBrush = SolidColor(
                    value = MaterialTheme.colorScheme.primary
                )
            )

            if (password.isEmpty()) {
                TextButton(
                    onClick = innerButtonClick,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = placeholderAlpha)
                    )
                ) {
                    Text(
                        text = innerButtonText,
                        style = MaterialTheme.typography.bodySmall
                    )
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
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EmptyPasswordTextFieldPreview() {
    NimbleTheme {
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
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NotEmptyPasswordTextFieldPreview() {
    NimbleTheme {
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
}
