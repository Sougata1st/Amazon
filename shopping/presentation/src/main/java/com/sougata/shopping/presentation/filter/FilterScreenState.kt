package com.sougata.shopping.presentation.filter

data class FilterScreenState(
    val selectedCategory: String = "",
    val selectedSortBy: String = "basePrice",
    val selectedSortDirection: String = "ASC",
    val categoryList: List<String> = emptyList(),
    val sortByList: List<String> = listOf("productName", "basePrice", "stocks", "category"),
    val sortDirectionList: List<String> = listOf("ASC", "DESC"),
    val priceRange: ClosedFloatingPointRange<Float> = 0f..10000f
)

