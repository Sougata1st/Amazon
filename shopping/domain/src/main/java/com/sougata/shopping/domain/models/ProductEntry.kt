package com.sougata.shopping.domain.models

data class ProductEntry(
    val product: ProductCart,
    val quantity: Int
)


data class ProductCart(
    val productId: String,
    val productName: String,
    val basePrice: Double,
    val stocks: Int,
    val imageUrl: String,
    val category: String,
    val modifiedPrice: Double,
    val unavaliable: Boolean
)