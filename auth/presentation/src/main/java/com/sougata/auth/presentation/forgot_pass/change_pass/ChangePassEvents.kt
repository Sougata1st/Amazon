package com.sougata.auth.presentation.forgot_pass.change_pass

import com.sougata.core.presentation.ui.UiText

sealed interface ChangePassEvents {
    data object Success: ChangePassEvents
    data class Failure(val msg: UiText): ChangePassEvents
}