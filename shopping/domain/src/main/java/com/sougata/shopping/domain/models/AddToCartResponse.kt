package com.sougata.shopping.domain.models

data class AddToCartResponse(
    val data: String,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)


