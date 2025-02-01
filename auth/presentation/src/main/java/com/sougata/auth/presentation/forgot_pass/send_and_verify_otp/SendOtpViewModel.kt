package com.sougata.auth.presentation.forgot_pass.send_and_verify_otp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.auth.domain.AuthRepository
import com.sougata.auth.presentation.util.isValidEmail
import com.sougata.core.domain.util.Result
import com.sougata.core.presentation.ui.asUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SendOtpViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(SendOtpState())

    private val eventChannel = Channel<SendOtpEvents>()
    val events = eventChannel.receiveAsFlow()


    val canVerifyOtp = snapshotFlow { state.email }.combine(
        snapshotFlow { state.otp }
    ) { email, otp ->
        email.isValidEmail() && otp.length == 6
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    val canSendOtp = snapshotFlow { state.email }.map {
        it.isValidEmail()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)


    fun onAction(action: SendOtpActions) {
        when (action) {
            is SendOtpActions.EnteredEmail -> state = state.copy(email = action.email)
            is SendOtpActions.EnteredOtp -> state = state.copy(otp = action.otp)
            SendOtpActions.SendOtpClicked -> sendOtp()
            SendOtpActions.VerifyOtpClicked -> verifyOtp()
        }
    }

    private fun verifyOtp() {
        viewModelScope.launch {
            state = state.copy(
                isVerifyingOtp = true
            )
            val result = authRepository.verifyOtp(email = state.email, otp = state.otp)
            if (result is Result.Success) {
                eventChannel.send(SendOtpEvents.VerifiedOtp)
            } else if (result is Result.Error) {
                eventChannel.send(SendOtpEvents.FailedToSendOtp(result.error.asUiText()))
            }
        }
    }

    private fun sendOtp() {
        viewModelScope.launch {
            state = state.copy(
                isSendingOtp = true
            )
            val result = authRepository.sendOtp(email = state.email)
            if (result is Result.Success) {
                eventChannel.send(SendOtpEvents.SentOtp)
            } else if (result is Result.Error) {
                eventChannel.send(SendOtpEvents.FailedToSendOtp(result.error.asUiText()))
            }
            state = state.copy(
                isSendingOtp = false
            )
        }
    }

}