package com.kyawlinnthant.nimble.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.kyawlinnthant.nimble.R
import com.kyawlinnthant.theme.NimbleTheme
import com.kyawlinnthant.theme.dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginAgainDialog(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.logout_alert),
    description: String,
    onClick: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
    ) {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(MaterialTheme.dimen.base2x))
                .background(MaterialTheme.colorScheme.surface)
                .padding(MaterialTheme.dimen.base3x),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = modifier.height(MaterialTheme.dimen.base3x))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = modifier.height(MaterialTheme.dimen.base3x))
            Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                Button(onClick = onClick) {
                    Text(text = stringResource(id = R.string.login_again))
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    NimbleTheme {
        Surface {
            LoginAgainDialog(
                title = "Device Login Alert",
                description = "Client authentication failed due to unknown client, no client authentication included, or unsupported authentication method.",
                onClick = {},
            )
        }
    }
}
