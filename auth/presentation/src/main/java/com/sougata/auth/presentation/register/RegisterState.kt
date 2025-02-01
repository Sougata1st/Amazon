package com.sougata.auth.presentation.register

import com.sougata.auth.presentation.register.components.PassStateEnum
import com.sougata.auth.presentation.util.isValidEmail

data class RegisterState(
    val email: String = "",
    val pass: String = "",
    val confirmPass: String = "",
    val isRegistering: Boolean = false
) {
    val canRegister: Boolean
        get() = pass.isNotEmpty()&& confirmPass.isNotEmpty() && email.isValidEmail() && pass == confirmPass

    val passState: PassStateEnum
        get() = when {
            pass.isEmpty() && confirmPass.isEmpty() -> PassStateEnum.EMPTY
            pass == confirmPass -> PassStateEnum.MATCHES
            else -> PassStateEnum.DOES_NOT_MATCH
        }
}
