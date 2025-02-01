package com.sougata.shopping.presentation.homeRoot.home

import com.sougata.shopping.presentation.homeRoot.util.Product

data class HomeScreenState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val cartCount: Int = 0,
    val cartScreenState: CartScreenState = CartScreenState()
)

class CartScreenState(val cartItemId : String = "",val isAddingToCart: Boolean = false )