package com.sougata.auth.presentation.forgot_pass.change_pass

sealed interface ChangePassActions {
    data class EnteredPass(val pass: String) : ChangePassActions
    data class EnteredConfirmPass(val confirmPass: String) : ChangePassActions
    data object ConfirmPassClicked : ChangePassActions
}