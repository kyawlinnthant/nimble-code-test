package com.kyawlinnthant.presentation.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.kyawlinnthant.auth.presentation.R
import com.kyawlinnthant.theme.dimen
import com.kyawlinnthant.util.AppConstant

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String = "",
    onValueChanged: (String) -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Go,
    onValueCleared: () -> Unit = {},
    isError: Boolean = false,
    errorMessage: String = "",
    singleLine: Boolean = true,
    keyboardAction: (KeyboardActionScope) -> Unit = {},
    isContainInnerButton: Boolean = false,
    innerButtonText: String = "",
    innerButtonClick: () -> Unit = {},
    backgroundAlpha: Float = 0.2f
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
                value = value,
                onValueChange = {
                    onValueChanged(it)
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    innerTextField()
                },
                singleLine = singleLine,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onDone = keyboardAction,
                    onGo = keyboardAction,
                    onNext = keyboardAction,
                    onPrevious = keyboardAction,
                    onSearch = keyboardAction,
                    onSend = keyboardAction
                ),
                cursorBrush = SolidColor(
                    value = MaterialTheme.colorScheme.primary
                )
            )
            if (value.isNotEmpty()) {
                IconButton(onClick = onValueCleared) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_clear_24),
                        contentDescription = "Trailing Icon",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant

                    )
                }
            }
            if (isContainInnerButton) {
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
fun VisibilityAnimator(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    errorMessage: String,
    hasInfo: Boolean? = false
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(AppConstant.DEFAULT_MILLI)) +
            expandVertically(animationSpec = tween(AppConstant.DEFAULT_MILLI)),
        exit = fadeOut(animationSpec = tween(AppConstant.DEFAULT_MILLI)) +
            shrinkVertically(animationSpec = tween(AppConstant.DEFAULT_MILLI))
    ) {
        val colorValue = if (hasInfo!!) {
            MaterialTheme.colorScheme.outline
        } else {
            MaterialTheme.colorScheme.error
        }
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    horizontal = MaterialTheme.dimen.base2x,
                    vertical = MaterialTheme.dimen.small
                ),
            text = errorMessage,
            style = MaterialTheme.typography.bodySmall,
            color = colorValue
        )
    }
}
