package com.sougata.shopping.presentation.homeRoot.util

data class ProductCartItem(
    val id: String,
    val productId: String,
    val productName: String,
    val basePrice: String,
    val stocks: String,
    val imageUrl: String,
    val category: String,
    val modifiedPrice: String,
    val unavaliable: String,
    val rating: String,
    val noOfRating: String,
    val discountPercentage: String,
    val cartCount: String
)

