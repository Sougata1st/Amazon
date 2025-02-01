package com.sougata.shopping.presentation.filter

import com.sougata.core.presentation.ui.UiText

sealed interface FilterScreenEvents {
    data object Success: FilterScreenEvents
    data class Failure(val msg: UiText): FilterScreenEvents
}