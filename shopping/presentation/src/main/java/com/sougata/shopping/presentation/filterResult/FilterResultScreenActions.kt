package com.sougata.shopping.presentation.filterResult

import com.sougata.shopping.presentation.homeRoot.util.Product

interface FilterResultScreenActions {
    data class AddToCartClicked(val product: Product) : FilterResultScreenActions
}