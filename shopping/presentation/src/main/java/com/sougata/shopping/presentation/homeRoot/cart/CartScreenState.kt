package com.sougata.shopping.presentation.homeRoot.cart

import com.sougata.shopping.domain.models.AddressResponse
import com.sougata.shopping.presentation.homeRoot.util.ProductCartItem

data class CartScreenState(
    val products: List<ProductCartItem> = emptyList(),
    val cartAddRemove: CartAddRemove = CartAddRemove(),
    val totalSUm:Double = 0.0,
    val addressList : List<AddressResponse> = emptyList(),
    val addressId:Int = -1,
    val amount: Int = 0,
    val key: String = "",
    val orderId: String = ""
)

class CartAddRemove(val cartItemId : String = "",val isDeleting: Boolean = false )