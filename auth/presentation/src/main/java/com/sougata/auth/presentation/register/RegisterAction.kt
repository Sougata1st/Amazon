package com.sougata.auth.presentation.register

sealed interface RegisterAction {
    data class EnteredEmail(val email: String): RegisterAction
    data class EnteredPass(val pass: String): RegisterAction
    data class EnteredConfirmPass(val confirmPass: String): RegisterAction
    data object RegisterClicked: RegisterAction
}