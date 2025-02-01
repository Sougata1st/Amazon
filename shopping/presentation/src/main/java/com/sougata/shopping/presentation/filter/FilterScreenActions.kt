package com.sougata.shopping.presentation.filter

sealed interface FilterScreenActions {
    data class CategorySelectionTabClicked(val category: String) : FilterScreenActions
    data class SortBySelectionTabClicked(val sortBy: String) : FilterScreenActions
    data class PriceBoundSelectionTabClicked(val priceRange: ClosedFloatingPointRange<Float>) : FilterScreenActions
    data class SortDirectionSelectionTabClicked(val sortDir: String) : FilterScreenActions
    data object ApplyClicked : FilterScreenActions
}

//category
//sortDir
//priceBound
//sortBy
