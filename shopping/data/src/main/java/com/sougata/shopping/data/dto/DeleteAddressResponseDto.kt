package com.sougata.shopping.data.dto

import com.sougata.shopping.domain.models.DeleteAddressResponse
import kotlinx.serialization.Serializable

@Serializable
data class DeleteAddressResponseDto(
    val data: String,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)


fun DeleteAddressResponseDto.toDeleteAddressResponse() = DeleteAddressResponse(
    data = this.data,
    status = this.status,
    success = this.success,
    error = this.error,
    timeStamp = this.timeStamp
)