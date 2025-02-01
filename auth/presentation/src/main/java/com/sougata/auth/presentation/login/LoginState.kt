package com.sougata.auth.presentation.login

import com.sougata.auth.presentation.util.isValidEmail

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoggingIn: Boolean = false
) {
    val canLogin = email.isValidEmail() && password.isNotEmpty()
}
