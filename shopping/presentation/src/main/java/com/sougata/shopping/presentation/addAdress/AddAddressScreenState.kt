package com.sougata.shopping.presentation.addAdress

data class AddAddressScreenState(
    val locality: String = "",
    val landmark: String = "",
    val state: String = "",
    val zipCode: String = "",
    val addressType: String = "",
    val phoneNumber: String = "",
    val isSavingAddress: Boolean = false
)


