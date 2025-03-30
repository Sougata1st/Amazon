package com.sougata.shopping.presentation.homeRoot.orders

data class OrderItemUiState(
    val orderId: String = "",
    val status: String = "",
    val createdAt: String = "",
    val totalPrice: String = "",
    val productList: List<ProductUiState> = emptyList(),
    val address: AddressUiState = AddressUiState()
)

data class ProductUiState(
    val productName: String = "",
    val productImageUrl: String = "",
    val productPrice: String = "",
    val quantity: Int = 0
)

data class AddressUiState(
    val locality: String = "",
    val landmark: String = "",
    val state: String = "",
    val zipCode: String = "",
    val addressType: String = "",
    val phoneNumber: String = ""
)