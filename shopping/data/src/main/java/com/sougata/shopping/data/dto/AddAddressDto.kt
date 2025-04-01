package com.sougata.shopping.data.dto

import com.sougata.shopping.domain.models.AddressResponse
import kotlinx.serialization.Serializable


@Serializable
data class AddAddressResponseDTO(
    val data: AddressDTO1,
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

