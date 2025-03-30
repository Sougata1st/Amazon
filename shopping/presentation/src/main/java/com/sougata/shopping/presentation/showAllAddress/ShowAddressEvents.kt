package com.sougata.shopping.presentation.showAllAddress

import com.sougata.core.presentation.ui.UiText

sealed interface ShowAddressEvents {
    data object Success: ShowAddressEvents
    data class Failure(val msg: UiText): ShowAddressEvents

}