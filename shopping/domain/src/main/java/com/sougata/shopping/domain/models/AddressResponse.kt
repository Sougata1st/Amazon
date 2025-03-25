package com.sougata.shopping.domain.models

data class AddressResponse (
    val locality: String,
    val landmark: String,
    val state: String,
    val zipCode: String,
    val addressType: String,
    val id: Int,
    val phoneNumber: String
)

