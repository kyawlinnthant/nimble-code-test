package com.kyawlinnthant.presentation.surveys.view

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.kyawlinnthant.theme.NimbleTheme
import com.kyawlinnthant.theme.dimen

@Composable
fun FirstTimeLoadingView(
    modifier: Modifier = Modifier
) {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.5f),
        Color.LightGray.copy(alpha = 0.3f),
        Color.LightGray.copy(alpha = 0.5f)
    )
    val transition = rememberInfiniteTransition(label = "transition")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(
            x = translateAnim.value,
            y = translateAnim.value
        )
    )
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
            .padding(top = MaterialTheme.dimen.base3x, bottom = MaterialTheme.dimen.base3x)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimen.base2x),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                ShimmerItem(fraction = 0.35f, brush = brush)
                Spacer(modifier = modifier.height(MaterialTheme.dimen.base))
                ShimmerItem(fraction = 0.3f, brush = brush)
            }
            ProfileShimmer(brush = brush)
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimen.base2x)
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start
        ) {
            ShimmerItem(fraction = 0.15f, brush = brush)
            Spacer(modifier = modifier.height(MaterialTheme.dimen.base))
            ShimmerItem(fraction = 0.75f, brush = brush)
            Spacer(modifier = modifier.height(MaterialTheme.dimen.base))
            ShimmerItem(fraction = 0.35f, brush = brush)
            Spacer(modifier = modifier.height(MaterialTheme.dimen.base2x))
            ShimmerItem(fraction = 0.85f, brush = brush)
            Spacer(modifier = modifier.height(MaterialTheme.dimen.base))
            ShimmerItem(fraction = 0.5f, brush = brush)
        }
    }
}

@Composable
fun ProfileShimmer(
    modifier: Modifier = Modifier,
    brush: Brush,
    size: Dp = MaterialTheme.dimen.profile
) {
    Spacer(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(brush)
    )
}

@Composable
fun ShimmerItem(
    modifier: Modifier = Modifier,
    fraction: Float,
    brush: Brush,
    height: Dp = MaterialTheme.dimen.shimmer
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth(fraction)
            .height(height)
            .clip(CircleShape)
            .background(brush)
    )
}

@Composable
@Preview
private fun Preview() {
    NimbleTheme {
        Surface {
            FirstTimeLoadingView()
        }
    }
}
