package com.sougata.shopping.domain.models

data class Address(
    val locality: String,
    val landmark: String,
    val state: String,
    val zipCode: String,
    val addressType: String,
    val phoneNumber: String
)
