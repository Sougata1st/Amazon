package com.sougata.shopping.presentation.filterResult

import com.sougata.shopping.presentation.homeRoot.home.CartScreenState
import com.sougata.shopping.presentation.homeRoot.util.Product

data class FilterResultScreenState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val cartCount: Int = 0,
    val cartScreenState: CartScreenState = CartScreenState()
)
