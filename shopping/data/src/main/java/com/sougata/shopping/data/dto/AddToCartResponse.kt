package com.sougata.shopping.data.dto

import com.sougata.shopping.domain.models.AddToCartResponse
import kotlinx.serialization.Serializable

@Serializable
data class AddToCartSerializable(
    val data: String,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)

fun AddToCartResponse.toAddToCartSerializable(): AddToCartSerializable {
    return AddToCartSerializable(
        data = data,
        status = status,
        success = success,
        error = error,
        timeStamp = timeStamp
    )
}

fun AddToCartSerializable.toAddToCartResponse(): AddToCartResponse {
    return AddToCartResponse(
        data = data,
        status = status,
        success = success,
        error = error,
        timeStamp = timeStamp
    )
}