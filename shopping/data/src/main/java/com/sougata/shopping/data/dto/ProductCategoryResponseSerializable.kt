package com.sougata.shopping.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategoryResponseSerializable(
    val data: List<ProductCategorySerializable>,
    val status: Int,
    val success: Boolean,
    val error: String? = null,
    val timeStamp: String
)
