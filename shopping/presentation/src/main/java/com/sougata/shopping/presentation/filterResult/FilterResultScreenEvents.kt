package com.sougata.shopping.presentation.filterResult

import com.sougata.core.presentation.ui.UiText

interface FilterResultScreenEvents {
    data object Success: FilterResultScreenEvents
    data class Failure(val msg: UiText): FilterResultScreenEvents
}