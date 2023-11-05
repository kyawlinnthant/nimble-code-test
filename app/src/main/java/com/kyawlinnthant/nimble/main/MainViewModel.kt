package com.kyawlinnthant.nimble.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kyawlinnthant.domain.usecase.GetIsAuthenticated
import com.kyawlinnthant.navigation.navigator.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    private val getIsAuthenticated: GetIsAuthenticated,
) : ViewModel() {

    private val vmLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn get() = vmLoggedIn.asStateFlow()

    init {
        getAuthenticated()
    }

    private fun getAuthenticated() {
        viewModelScope.launch {
            getIsAuthenticated().collect {
                vmLoggedIn.emit(it)
            }
        }
    }
}
