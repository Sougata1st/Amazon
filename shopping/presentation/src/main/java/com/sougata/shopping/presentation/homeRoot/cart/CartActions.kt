package com.sougata.shopping.presentation.homeRoot.cart

import com.sougata.shopping.presentation.homeRoot.util.ProductCartItem

sealed interface CartActions {
    data class DeleteWholeProduct(val productId: String) : CartActions
    data class DecrementOneProduct(val productId: String) : CartActions
    data class IncrementOneProduct(val productCartItem: ProductCartItem) : CartActions
    data object Purchase: CartActions
}