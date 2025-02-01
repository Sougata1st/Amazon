package com.sougata.shopping.domain.models

data class ProductCategoryResponse(
    val data: List<ProductCategory>,
    val status: Int,
    val success: Boolean,
    val error: String? = null,
    val timeStamp: String
)
