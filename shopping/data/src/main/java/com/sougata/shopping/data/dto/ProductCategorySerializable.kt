package com.sougata.shopping.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductCategorySerializable(
    val id: Int,
    val name: String
)