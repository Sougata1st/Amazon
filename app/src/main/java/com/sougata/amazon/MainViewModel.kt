package com.sougata.amazon

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.core.domain.SessionStorage
import kotlinx.coroutines.launch

class MainViewModel(
    private val sessionStorage: SessionStorage
): ViewModel() {
    var authState by mutableStateOf(AuthState())

    init {
        viewModelScope.launch {
            authState = authState.copy(isCheckingAuth = true)
            val authInfo = sessionStorage.get()
            authState = authState.copy(isLoggedIn = authInfo != null)
            authState = authState.copy(isCheckingAuth = false)
        }
    }
}