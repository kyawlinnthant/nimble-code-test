package com.kyawlinnthant.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyawlinnthant.domain.usecase.Login
import com.kyawlinnthant.domain.usecase.ValidateLogin
import com.kyawlinnthant.network.util.DataResult
import com.kyawlinnthant.presentation.login.udf.LoginAction
import com.kyawlinnthant.presentation.login.udf.LoginEvent
import com.kyawlinnthant.presentation.login.udf.LoginViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: Login,
    private val validate : ValidateLogin
) : ViewModel(){

    private val vmEvent = MutableSharedFlow<LoginEvent>()
    val uiEvent get() = vmEvent.asSharedFlow()

    private val vmState = MutableStateFlow(LoginViewModelState())
    val form = vmState
        .map(LoginViewModelState::asForm)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asForm()
        )

    val error = vmState
        .map(LoginViewModelState::asError)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = vmState.value.asError()
        )

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.Login -> doValidate()
            LoginAction.ForgotPassword -> {
                // navigate to forgot password
            }
            is LoginAction.UpdateEmail -> {
                vmState.update { state ->
                    state.copy(
                        form = vmState.value.form.copy(
                            email = action.email
                        )
                    )
                }
            }
            is LoginAction.UpdatePassword -> {
                vmState.update { state ->
                    state.copy(
                        form = vmState.value.form.copy(
                            password = action.password
                        )
                    )
                }
            }
        }
    }

    private fun doValidate() {
        viewModelScope.launch {
            validate(form = vmState.value.form).apply {
                vmState.update { state ->
                    state.copy(
                        error = this
                    )
                }
            }.also {
                if (!it.isErrorEmail && !it.isErrorPassword){
                    doLogin()
                }
            }
        }
    }
    private fun doLogin() {
        viewModelScope.launch {
            when (val response = login(
                form = vmState.value.form
            )) {
                is DataResult.Failed -> {
                    //show error
                    vmEvent.emit(LoginEvent.ShowSnack(response.message))
                }
                is DataResult.Success -> {
                    // navigate to SurveysScreen
                    vmEvent.emit(LoginEvent.ShowSnack("Success"))
                }
            }

        }
    }
}