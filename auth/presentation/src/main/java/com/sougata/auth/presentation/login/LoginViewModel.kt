package com.sougata.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.auth.domain.AuthRepository
import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.Result
import com.sougata.core.presentation.ui.UiText
import com.sougata.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
):ViewModel() {
    var state by mutableStateOf(LoginState())
        private set

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.EnteredEmail -> {
                state = state.copy(email = action.email)
            }

            is LoginAction.EnteredPass -> {
                state = state.copy(password = action.pass)
            }

            LoginAction.LoginClicked -> {
                login()
            }

            else -> Unit
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoggingIn = true)
            val result = authRepository.login(
                email = state.email,
                password = state.password
            )
            state = state.copy(isLoggingIn = false)

            when(result){
                is Result.Error -> {
                    if (result.error == DataError.Network.UNAUTHORIZED){
                        eventChannel.send(LoginEvent.LoginError(UiText.DynamicString("Invalid Credentials")))
                    }else{
                        eventChannel.send(LoginEvent.LoginError(result.error.asUiText()))
                    }
                }
                is Result.Success -> {
                    eventChannel.send(LoginEvent.LoginSuccess)
                }
            }
        }
    }
}