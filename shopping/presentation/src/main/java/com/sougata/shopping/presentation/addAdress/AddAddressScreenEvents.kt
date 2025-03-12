package com.sougata.shopping.presentation.addAdress

import com.sougata.core.presentation.ui.UiText

interface AddAddressScreenEvents {
    data class Error(val error:UiText):AddAddressScreenEvents
    data object Success: AddAddressScreenEvents
}