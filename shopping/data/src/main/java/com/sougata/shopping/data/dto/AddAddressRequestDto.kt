package com.sougata.shopping.data.dto

import com.sougata.shopping.domain.models.Address
import kotlinx.serialization.Serializable

@Serializable
data class AddAddressRequestDto(
    val locality: String,
    val landmark: String,
    val state: String,
    val zipCode: String,
    val addressType: String,
    val phoneNumber: String
)



fun Address.toAddAddressRequestDtoSerializable() = AddAddressRequestDto(
    locality = this.locality,
    landmark = this.landmark,
    state = this.state,
    zipCode = this.zipCode,
    addressType = this.addressType,
    phoneNumber = this.phoneNumber
)
