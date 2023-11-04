package com.kyawlinnthant.presentation.surveys.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kyawlinnthant.home.presentation.R
import com.kyawlinnthant.theme.NimbleTheme
import com.kyawlinnthant.theme.dimen


@Composable
fun FirstTimeErrorView(
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
    error: String,
) {
    Column(
        modifier = modifier.fillMaxSize().testTag("first_time_error"),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = error,
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimen.base),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(MaterialTheme.dimen.base2x))
        OutlinedButton(onClick = onRetry) {
            Text(text = stringResource(R.string.retry))
        }
    }
}

@Composable
@Preview
private fun Preview() {
    NimbleTheme {
        Surface {
            FirstTimeErrorView(
                onRetry = {},
                error = "Something is wrong"
            )
        }
    }
}