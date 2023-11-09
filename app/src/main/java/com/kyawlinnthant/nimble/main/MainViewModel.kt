package com.kyawlinnthant.nimble.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyawlinnthant.domain.usecase.GetIsAuthenticated
import com.kyawlinnthant.domain.usecase.Logout
import com.kyawlinnthant.navigation.Routes
import com.kyawlinnthant.navigation.navigator.AppNavigator
import com.kyawlinnthant.network.forbidden.LogoutAlert
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    private val getIsAuthenticated: GetIsAuthenticated,
    private val logoutAlert: LogoutAlert,
    private val clearPref: Logout,
) : ViewModel() {

    private val vmState = MutableStateFlow(MainViewModelState())

    val isLoggedIn = vmState
        .map(MainViewModelState::asLoggedIn)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asLoggedIn(),
        )

    val isLoggedOut = vmState
        .map(MainViewModelState::asLoggedOut)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asLoggedOut(),
        )

    init {
        getAuthenticated()
        listenLogoutAlert()
    }

    private fun listenLogoutAlert() {
        viewModelScope.launch {
            logoutAlert.logoutMessage.consumeAsFlow().collect {
                vmState.update { state ->
                    state.copy(
                        hasLoggedOut = it,
                    )
                }
            }
        }
    }

    private fun getAuthenticated() {
        viewModelScope.launch {
            getIsAuthenticated().collect {
                vmState.update { state ->
                    state.copy(
                        isAdyLoggedIn = it,
                    )
                }
            }
        }
    }

    fun loginAgain() {
        appNavigator.to(
            route = Routes.AUTH,
            popupToRoute = Routes.HOME,
            inclusive = true,
        )
        vmState.update { state ->
            state.copy(
                hasLoggedOut = "",
            )
        }
        viewModelScope.launch {
            clearPref()
        }
    }
}
