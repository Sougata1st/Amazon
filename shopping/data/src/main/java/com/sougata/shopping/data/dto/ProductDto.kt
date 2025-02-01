package com.sougata.shopping.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val productId: String,
    val productName: String,
    val basePrice: Double,
    val stocks: Int,
    val imageUrl: String,
    val category: String,
    val modifiedPrice: Double,
    val unavaliable: Boolean // Corrected typo
)
@Serializable
data class ProductDataDto(
    val content: List<ProductDto>,
    val currentPage: Int,
    val pageSize: Int,
    val totalItems: Int,
    val currentPageItemsNumber: Int,
    val totalPages: Int,
    val lastPage: Boolean
)

@Serializable
data class ProductResponse(
    val data: ProductDataDto,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)