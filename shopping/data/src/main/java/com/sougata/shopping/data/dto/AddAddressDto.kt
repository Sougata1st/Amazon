package com.sougata.shopping.data.dto

import com.sougata.shopping.domain.models.AddressResponse
import kotlinx.serialization.Serializable

@Serializable
data class AddressDTO(
    val locality: String,
    val landmark: String,
    val state: String,
    val zipCode: String,
    val addressType: String,
    val id: Int,
    val phoneNumber: String
)

@Serializable
data class AddAddressResponseDTO(
    val data: AddressDTO,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)


fun AddAddressResponseDTO.toAddressResponse() = AddressResponse(
    locality = this.data.locality,
    landmark = this.data.landmark,
    state = this.data.state,
    zipCode = this.data.zipCode,
    addressType = this.data.addressType,
    id = this.data.id,
    phoneNumber = this.data.phoneNumber,
)

fun AddressDTO.toAddressResponse() = AddressResponse(
    locality = this.locality,
    landmark = this.landmark,
    state = this.state,
    zipCode = this.zipCode,
    addressType = this.addressType,
    id = this.id,
    phoneNumber = this.phoneNumber,
)