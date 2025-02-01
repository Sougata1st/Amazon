package com.sougata.auth.presentation.forgot_pass.change_pass

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.auth.domain.AuthRepository
import com.sougata.core.domain.util.Result
import com.sougata.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChangePassViewModel(
    private val authRepository: AuthRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(ChangePassState())

    private val _eventFlow = Channel<ChangePassEvents>()
    val events =_eventFlow.consumeAsFlow()

    val canChangePass = snapshotFlow { state.pass }
        .combine(snapshotFlow { state.confirmPass })
        { pass, confirmPass ->
            pass.isNotEmpty() && confirmPass.isNotEmpty() && pass == confirmPass
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    fun onAction(action: ChangePassActions){
        when(action){
            ChangePassActions.ConfirmPassClicked -> {
                changePass()
            }
            is ChangePassActions.EnteredConfirmPass -> state = state.copy(confirmPass = action.confirmPass)
            is ChangePassActions.EnteredPass -> state = state.copy(pass = action.pass)
        }
    }

    private fun changePass(){
        viewModelScope.launch {
            state = state.copy(isChangingPass = true)
            val email = savedStateHandle.get<String>("email")
            val result = authRepository.resetPassword(
                email = email!!,
                password = state.pass
            )
            when(result){
                is Result.Error -> {
                    _eventFlow.send(ChangePassEvents.Failure(result.error.asUiText()))
                }
                is Result.Success -> {
                    _eventFlow.send(ChangePassEvents.Success)
                }
            }
            state = state.copy(isChangingPass = false)
        }
    }
}