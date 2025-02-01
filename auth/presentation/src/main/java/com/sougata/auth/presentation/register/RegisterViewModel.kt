package com.sougata.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.auth.domain.AuthRepository
import com.sougata.auth.presentation.R
import com.sougata.core.domain.util.DataError
import com.sougata.core.presentation.ui.UiText
import com.sougata.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: AuthRepository
) : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    private val eventChannel = Channel<RegisterEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.EnteredConfirmPass -> {
                state = state.copy(
                    confirmPass = action.confirmPass
                )
            }

            is RegisterAction.EnteredEmail -> {
                state = state.copy(
                    email = action.email
                )
            }

            is RegisterAction.EnteredPass -> {
                state = state.copy(
                    pass = action.pass
                )
            }

            RegisterAction.RegisterClicked -> register()
        }
    }

    private fun register() {
        viewModelScope.launch {
            state = state.copy(
                isRegistering = true
            )
            val result = repository.register(
                email = state.email.trim(),
                password = state.pass
            )
            state = state.copy(
                isRegistering = false
            )
            when (result) {
                is com.sougata.core.domain.util.Result.Error -> {
                    if (result.error == DataError.Network.CONFLICT) {
                        eventChannel.send(RegisterEvent.RegisterError(UiText.StringResource(R.string.error_email_already_exists)))
                    } else {
                        eventChannel.send(RegisterEvent.RegisterError(result.error.asUiText()))
                    }
                }

                is com.sougata.core.domain.util.Result.Success -> {
                    eventChannel.send(RegisterEvent.RegisterSuccess)
                }
            }
        }
    }

}