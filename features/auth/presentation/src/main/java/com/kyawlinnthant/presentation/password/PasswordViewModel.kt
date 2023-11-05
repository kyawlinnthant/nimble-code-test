package com.kyawlinnthant.presentation.password

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyawlinnthant.navigation.Screens
import com.kyawlinnthant.navigation.navigator.AppNavigator
import com.kyawlinnthant.presentation.password.udf.PasswordAction
import com.kyawlinnthant.presentation.password.udf.PasswordViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val vmState = MutableStateFlow(PasswordViewModelState())
    val email = vmState
        .map(PasswordViewModelState::asEmail)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asEmail()
        )

    init {
        getEmail()
    }

    private fun getEmail() {
        savedStateHandle.get<String>(key = Screens.EMAIL)?.let {
            vmState.update { state ->
                state.copy(
                    email = it
                )
            }
        }
    }

    fun onAction(action: PasswordAction) {
        when (action) {
            PasswordAction.Back -> appNavigator.back()
        }
    }
}
