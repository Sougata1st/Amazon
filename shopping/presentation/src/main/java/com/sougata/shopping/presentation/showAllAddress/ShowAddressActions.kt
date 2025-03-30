package com.sougata.shopping.presentation.showAllAddress

sealed interface ShowAddressActions {
    data class DeleteClicked(val addressId: Int) : ShowAddressActions
}