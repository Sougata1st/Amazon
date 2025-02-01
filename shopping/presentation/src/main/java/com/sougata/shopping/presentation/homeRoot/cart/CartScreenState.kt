package com.sougata.shopping.presentation.homeRoot.cart

import com.sougata.shopping.presentation.homeRoot.util.ProductCartItem

data class CartScreenState(
    val products: List<ProductCartItem> = emptyList(),
    val cartAddRemove: CartAddRemove = CartAddRemove(),
    val totalSUm:Double = 0.0
)

class CartAddRemove(val cartItemId : String = "",val isDeleting: Boolean = false )