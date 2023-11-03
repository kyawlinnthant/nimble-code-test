package com.kyawlinnthant.presentation.surveys.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.kyawlinnthant.domain.vo.SurveysVo
import com.kyawlinnthant.home.presentation.R
import com.kyawlinnthant.presentation.surveys.udf.SurveysAction
import com.kyawlinnthant.theme.dimen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HasDataView(
    surveys: List<SurveysVo>,
    modifier: Modifier = Modifier,
    onAction: (SurveysAction) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { surveys.size })
    Scaffold(
        contentWindowInsets = WindowInsets(
            left = 0,
            top = 0,
            right = 0,
            bottom = 0,
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
                containerColor = MaterialTheme.colorScheme.onSurface
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
            contentAlignment = Alignment.BottomStart
        ) {
            AsyncImage(
                model = surveys[pagerState.currentPage].image,
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f))
            )
            HorizontalPager(
                state = pagerState,
                modifier = modifier.fillMaxSize()
            ) { page ->
                val currentSurvey = surveys[page]
                SurveyView(survey = currentSurvey)
            }
            PagerIndicator(size = surveys.size, currentPage = pagerState.currentPage)
        }
    }
}


@Composable
fun SurveyView(
    modifier: Modifier = Modifier,
    survey: SurveysVo
) {
    Box(
        modifier = modifier.fillMaxSize()

    ) {
        AsyncImage(
            model = survey.image,
            contentDescription = null,
            modifier = modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f))
        )

        Column(
            modifier = modifier
                .fillMaxSize()
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
                    Text(text = survey.id)
                }

            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.dimen.base2x)
                    .weight(1f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = survey.name, style = MaterialTheme.typography.displayLarge)
                Spacer(modifier = modifier.height(MaterialTheme.dimen.base2x))
                Text(text = survey.description, style = MaterialTheme.typography.labelLarge)
            }
        }

    }

}