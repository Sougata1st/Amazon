package com.sougata.auth.presentation.forgot_pass.send_and_verify_otp

sealed interface SendOtpActions {
    data class EnteredEmail(val email: String): SendOtpActions
    data object SendOtpClicked: SendOtpActions
    data class EnteredOtp(val otp: String): SendOtpActions
    data object VerifyOtpClicked: SendOtpActions
}