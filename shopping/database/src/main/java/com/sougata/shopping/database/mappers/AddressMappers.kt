package com.sougata.shopping.database.mappers

import com.sougata.shopping.database.entity.AddressEntity
import com.sougata.shopping.domain.models.AddressResponse

fun AddressEntity.toAddressResponse() = AddressResponse(
    locality = locality,
    landmark = landmark,
    state = state,
    zipCode = zipCode,
    addressType = addressType,
    id = id,
    phoneNumber = phoneNumber
)

fun AddressResponse.toAddressEntity() = AddressEntity(
    locality = locality,
    landmark = landmark,
    state = state,
    zipCode = zipCode,
    addressType = addressType,
    id = id,
    phoneNumber = phoneNumber
)