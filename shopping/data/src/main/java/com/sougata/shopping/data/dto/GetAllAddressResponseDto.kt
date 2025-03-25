package com.sougata.shopping.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetAllAddressResponseDto(
    val data: List<AddressDTO>,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)
