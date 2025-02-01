package com.sougata.shopping.domain.models


data class Product(
    val id: Int,
    val productId: String,
    val productName: String,
    val basePrice: Double,
    val stocks: Int,
    val imageUrl: String,
    val category: String,
    val modifiedPrice: Double,
    val unavaliable: Boolean,
    val pageCount: Int
)