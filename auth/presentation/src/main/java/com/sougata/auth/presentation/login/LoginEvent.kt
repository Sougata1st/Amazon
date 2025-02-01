package com.sougata.auth.presentation.login

import com.sougata.core.presentation.ui.UiText

sealed interface LoginEvent {
    data class LoginError(val error: UiText): LoginEvent
    data object LoginSuccess: LoginEvent
}