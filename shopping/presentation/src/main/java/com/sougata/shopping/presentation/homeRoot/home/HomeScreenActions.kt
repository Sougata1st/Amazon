package com.sougata.shopping.presentation.homeRoot.home
import com.sougata.shopping.presentation.homeRoot.util.Product

sealed interface HomeScreenActions {
    data class AddToCartClicked(val product: Product) : HomeScreenActions
}