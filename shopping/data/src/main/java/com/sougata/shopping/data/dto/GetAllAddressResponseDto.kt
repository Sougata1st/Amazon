package com.sougata.shopping.data.dto

import com.sougata.shopping.domain.models.AddressResponse
import kotlinx.serialization.Serializable

@Serializable
data class GetAllAddressResponseDto(
    val data: List<AddressDTO1>,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)


@Serializable
data class AddressDTO1(
    val locality: String,
    val landmark: String,
    val state: String,
    val zipCode: String,
    val addressType: String,
    val id: Int,
    val phoneNumber: String
)

fun AddressDTO1.toAddressResponse() = AddressResponse(
    locality = this.locality,
    landmark = this.landmark,
    state = this.state,
    zipCode = this.zipCode,
    addressType = this.addressType,
    id = this.id,
    phoneNumber = this.phoneNumber,
)


