package com.sougata.auth.presentation.forgot_pass.send_and_verify_otp

import com.sougata.core.presentation.ui.UiText

sealed interface SendOtpEvents {
    data object SentOtp: SendOtpEvents
    data object VerifiedOtp: SendOtpEvents
    data class FailedToVerifyOtp(val error: UiText):SendOtpEvents
    data class FailedToSendOtp(val error:UiText): SendOtpEvents
}