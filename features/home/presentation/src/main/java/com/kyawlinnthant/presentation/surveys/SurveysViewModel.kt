package com.kyawlinnthant.presentation.surveys

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyawlinnthant.domain.usecase.FetchSurveys
import com.kyawlinnthant.domain.usecase.GetSurveys
import com.kyawlinnthant.navigation.Screens
import com.kyawlinnthant.navigation.navigator.AppNavigator
import com.kyawlinnthant.network.util.DataResult
import com.kyawlinnthant.presentation.surveys.udf.SurveysAction
import com.kyawlinnthant.presentation.surveys.udf.SurveysViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveysViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val getSurveys: GetSurveys,
    private val fetchSurveys: FetchSurveys,
) : ViewModel() {

    private val vmState = MutableStateFlow(SurveysViewModelState())
    val uiState = vmState
        .map(SurveysViewModelState::asUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asUiState()
        )

    init {
        fetchSurveysFromServer()
        getSurveysFromDb()
    }

    private fun fetchSurveysFromServer() {
        vmState.update { state ->
            state.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            when (val data = fetchSurveys()) {
                is DataResult.Failed -> {
                    vmState.update { state ->
                        state.copy(
                            error = data.message
                        )
                    }
                }

                is DataResult.Success -> {
                    vmState.update { state ->
                        state.copy(
                            error = "",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    private fun getSurveysFromDb() {
        viewModelScope.launch {
            getSurveys().collect {
                vmState.update { state ->
                    state.copy(
                        surveys = it
                    )
                }
            }
        }
    }

    fun onAction(action: SurveysAction) {
        when (action) {
            is SurveysAction.GoToDetail -> {
                appNavigator.to(
                    route = Screens.SurveyDetail.passId(
                        id = action.id,
                        name = action.name
                    )
                )
            }

            SurveysAction.Retry -> {

            }
        }
    }
}