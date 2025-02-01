package com.sougata.auth.presentation.register

import com.sougata.core.presentation.ui.UiText

sealed interface RegisterEvent {
    data class RegisterError(val error: UiText): RegisterEvent
    data object RegisterSuccess: RegisterEvent
}