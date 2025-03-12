package com.sougata.shopping.presentation.addAdress

sealed interface AddAddressActions {
    data class AddAddressLocality(val locality: String) : AddAddressActions
    data class AddAddressLandmark(val landmark: String) : AddAddressActions
    data class AddAddressState(val state: String) : AddAddressActions
    data class AddAddressZipCode(val zipCode: String) : AddAddressActions
    data class AddAddressAddressType(val addressType: String) : AddAddressActions
    data class AddAddressPhoneNumber(val phoneNumber: String) : AddAddressActions
    object AddAddressClicked : AddAddressActions
}


enum class AddressTypes{
    HOME,
    OFFICE,
    OTHER
}

