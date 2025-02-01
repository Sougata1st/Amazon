package com.sougata.auth.presentation.forgot_pass.send_and_verify_otp

import com.sougata.auth.presentation.util.isValidEmail


data class SendOtpState (
    val email: String = "",
    val otp: String = "",
    val isSendingOtp: Boolean = false,
    val isVerifyingOtp: Boolean = false,
){
    val isEmailValid: Boolean = email.isValidEmail()
}