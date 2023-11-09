package com.kyawlinnthant.presentation.surveys.view

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.kyawlinnthant.domain.vo.SurveysVo
import com.kyawlinnthant.home.presentation.R
import com.kyawlinnthant.presentation.surveys.udf.SurveysAction
import com.kyawlinnthant.theme.NimbleTheme
import com.kyawlinnthant.theme.dimen
import com.kyawlinnthant.util.AppConstant
import com.kyawlinnthant.util.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HasDataView(
    surveys: List<SurveysVo>,
    modifier: Modifier = Modifier,
    onAction: (SurveysAction) -> Unit,
    profile: Color = MaterialTheme.colorScheme.onSurface,
    scrim: Brush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = AppConstant.SCRIM_ALPHA),
            MaterialTheme.colorScheme.surface.copy(alpha = AppConstant.SCRIM_ALPHA)
        )
    ),
    contentWeight: Float = 0.2f,
    spaceWeight: Float = 0.8f
) {
    val pagerState = rememberPagerState(pageCount = { surveys.size })
    Scaffold(
        contentWindowInsets = WindowInsets(
            left = 0,
            top = 0,
            right = 0,
            bottom = 0
        ),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAction(
                        SurveysAction.GoToDetail(
                            id = surveys[pagerState.currentPage].id,
                            name = surveys[pagerState.currentPage].name
                        )
                    )
                },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.onSurface,
                modifier = modifier.padding(bottom = MaterialTheme.dimen.base3x)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        }
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.BottomCenter
        ) {
            // Background Image
            AsyncImage(
                model = surveys[pagerState.currentPage].image,
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Scrim layout
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .drawBehind {
                        drawRect(
                            brush = scrim
                        )
                    }
            )
            // Profile Icon
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(
                        top = MaterialTheme.dimen.base4x,
                        end = MaterialTheme.dimen.base2x
                    ),
                contentAlignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = modifier
                        .size(MaterialTheme.dimen.profile)
                        .drawBehind {
                            drawCircle(color = profile)
                        }
                )
            }
            // Indicator
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = WindowInsets.navigationBars
                            .asPaddingValues()
                            .calculateBottomPadding()
                    )
                    .padding(
                        horizontal = MaterialTheme.dimen.standard
                    )
                    .padding(bottom = MaterialTheme.dimen.indicatorPadding),
                contentAlignment = Alignment.TopStart
            ) {
                PagerIndicator(
                    size = surveys.size,
                    currentPage = pagerState.currentPage
                )
            }

            // Pager
            HorizontalPager(
                state = pagerState,
                modifier = modifier.fillMaxSize()
            ) { page ->
                val currentSurvey = surveys[page]
                val createdDate = DateTimeFormatter.formatDateString(currentSurvey.createdAt)
                val activeDate =
                    DateTimeFormatter.formatDateString(currentSurvey.activeAt, justDayOfWeek = true)
                SurveyView(
                    createdAt = createdDate,
                    activeAt = activeDate,
                    title = currentSurvey.name,
                    description = currentSurvey.description
                )
            }
        }
    }
}

@Composable
fun SurveyView(
    modifier: Modifier = Modifier,
    maxLine: Int = 2,
    createdAt: String,
    activeAt: String,
    title: String,
    description: String,
    contentWeight: Float = 0.15f,
    spaceWeight: Float = 0.85f
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                bottom = WindowInsets.navigationBars
                    .asPaddingValues()
                    .calculateBottomPadding()
            )
            .padding(
                top = MaterialTheme.dimen.base3x,
                bottom = MaterialTheme.dimen.base3x
            ),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimen.base2x),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = createdAt.uppercase(),
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = modifier.height(MaterialTheme.dimen.base))
            Text(
                text = activeAt,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.dimen.base3x)
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimen.contentSection)
                .padding(MaterialTheme.dimen.base2x),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.displaySmall,
                maxLines = maxLine,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = MaterialTheme.dimen.base5x),
                maxLines = maxLine,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DarkPreview() {
    NimbleTheme {
        Surface {
            HasDataView(
                surveys = listOf(
                    SurveysVo(
                        id = "Today",
                        name = "Working from home Check-In",
                        description = "Would you like to know how you feel like our work from home",
                        image = "",
                        createdAt = "2017-01-23T07:48:12.991Z",
                        activeAt = "2017-01-23T07:48:12.991Z"
                    ),
                    SurveysVo(
                        id = "Today1",
                        name = "Working from home Check-In",
                        description = "Would you like to know how you feel like our work from home",
                        image = "",
                        createdAt = "2017-01-23T07:48:12.991Z",
                        activeAt = "2017-01-23T07:48:12.991Z"
                    )
                ),
                onAction = {}
            )
        }
    }
}

@Composable
@Preview
private fun LightPreview() {
    NimbleTheme {
        Surface {
            HasDataView(
                surveys = listOf(
                    SurveysVo(
                        id = "Today",
                        name = "Working from home Check-In",
                        description = "Would you like to know how you feel like our work from home",
                        image = "",
                        createdAt = "2017-01-23T07:48:12.991Z",
                        activeAt = "2017-01-23T07:48:12.991Z"
                    ),
                    SurveysVo(
                        id = "Today1",
                        name = "Working from home Check-In",
                        description = "Would you like to know how you feel like our work from home",
                        image = "",
                        createdAt = "2017-01-23T07:48:12.991Z",
                        activeAt = "2017-01-23T07:48:12.991Z"
                    )
                ),
                onAction = {}
            )
        }
    }
}
