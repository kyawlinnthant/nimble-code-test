package com.kyawlinnthant.presentation.surveys.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.kyawlinnthant.theme.dimen

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    size: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = MaterialTheme.dimen.base2x,
                bottom = MaterialTheme.dimen.base2x,
                end = MaterialTheme.dimen.base2x
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(size) {
            IndicateIcon(
                isSelected = it == currentPage
            )
        }
    }

}

@Composable
fun IndicateIcon(
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    val width = animateDpAsState(
        targetValue = if (isSelected) MaterialTheme.dimen.base3x else MaterialTheme.dimen.base,
        label = ""
    )

    Box(
        modifier = modifier
            .padding(MaterialTheme.dimen.base)
            .height(MaterialTheme.dimen.base)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurface.copy(0.5f)

            )
    )
}
