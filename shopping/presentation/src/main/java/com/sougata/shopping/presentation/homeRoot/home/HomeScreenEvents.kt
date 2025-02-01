package com.sougata.shopping.presentation.homeRoot.home

import com.sougata.core.presentation.ui.UiText

sealed interface HomeScreenEvents {
    data object Success: HomeScreenEvents
    data class Failure(val msg: UiText): HomeScreenEvents
}