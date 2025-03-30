package com.sougata.shopping.presentation.homeRoot.cart

import com.sougata.core.presentation.ui.UiText

sealed interface CartScreenEvents {
    data object Success: CartScreenEvents
    data object FailedToFetchCarts:CartScreenEvents
    data object InitialisePayment:CartScreenEvents
    data class Failure(val msg: UiText): CartScreenEvents
}