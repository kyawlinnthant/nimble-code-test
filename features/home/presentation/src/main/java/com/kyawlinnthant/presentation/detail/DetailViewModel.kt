package com.kyawlinnthant.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyawlinnthant.navigation.Screens
import com.kyawlinnthant.navigation.navigator.AppNavigator
import com.kyawlinnthant.presentation.detail.udf.DetailAction
import com.kyawlinnthant.presentation.detail.udf.DetailViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val vmState = MutableStateFlow(DetailViewModelState())
    val surveyId = vmState
        .map(DetailViewModelState::asId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asId()
        )

    val surveyName = vmState
        .map(DetailViewModelState::asName)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asName()
        )

    init {
        getId()
        getName()
    }

    fun onAction(action: DetailAction) {
        when (action) {
            DetailAction.Back -> appNavigator.back()
        }
    }

    private fun getId() {
        savedStateHandle.get<String>(key = Screens.DETAIL_ID)?.let {
            vmState.update { state ->
                state.copy(
                    id = it
                )
            }
        }
    }

    private fun getName() {
        savedStateHandle.get<String>(key = Screens.DETAIL_NAME)?.let {
            vmState.update { state ->
                state.copy(
                    name = it
                )
            }
        }
    }
}