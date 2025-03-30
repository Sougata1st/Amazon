package com.sougata.shopping.presentation.showAllAddress

import com.sougata.shopping.domain.models.AddressResponse

data class ShowAddressState(
    val addressList: List<AddressResponse> = emptyList(),
    val addressAddRemove: AddressAddRemove = AddressAddRemove()
)


class AddressAddRemove(val addressId : Int = -1,val isDeleting: Boolean = false )
