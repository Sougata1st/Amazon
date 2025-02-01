package com.sougata.shopping.data.dto
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    val data: DataDto,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)

@Serializable
data class DataDto(
    val products: List<ProductEntryDto>,
    val totalPrice: Double,
    val discountedPrice: Double,
    val discount: Double,
    val totalItems: Int
)

@Serializable
data class ProductEntryDto(
    val product: ProductCartDto,
    val quantity: Int
)

@Serializable
data class ProductCartDto(
    val productId: String,
    val productName: String,
    val basePrice: Double,
    val stocks: Int,
    val imageUrl: String,
    val category: String,
    val modifiedPrice: Double,
    val unavaliable: Boolean
)