package com.sougata.shopping.presentation.homeRoot.profile

import com.sougata.core.presentation.ui.UiText

interface ProfileScreenEvents {
    data object Success: ProfileScreenEvents
    data object NavigateToLogin: ProfileScreenEvents
    data class Error(val error: UiText): ProfileScreenEvents
}