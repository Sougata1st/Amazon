package com.sougata.auth.presentation.login

sealed interface LoginAction {
    data object LoginClicked: LoginAction
    data object ForgotPassClicked: LoginAction
    data object ConditionsOfUseClicked: LoginAction
    data object PrivacyNoticeClicked: LoginAction
    data class EnteredEmail(val email:String):LoginAction
    data class EnteredPass(val pass: String): LoginAction
}