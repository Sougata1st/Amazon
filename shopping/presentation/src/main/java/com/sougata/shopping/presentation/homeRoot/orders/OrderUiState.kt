package com.sougata.shopping.presentation.homeRoot.orders

import com.sougata.shopping.domain.models.Address2
import com.sougata.shopping.domain.models.Order
import com.sougata.shopping.domain.models.ProductOrder

data class OrderUiState(
    val isLoading: Boolean = false,
    val orders: List<OrderItemUiState> = emptyList(),
)



fun Order.toOrderItemUiState(): OrderItemUiState {
    return OrderItemUiState(
        orderId = razorpayOrderId,
        status = status,
        createdAt = createdAt,
        totalPrice = "₹$finalPrice",
        productList = products.map { it.toProductUiState() },
        address = address.toAddressUiState()
    )
}

fun ProductOrder.toProductUiState(): ProductUiState {
    return ProductUiState(
        productName = productResponse.productName,
        productImageUrl = productResponse.imageUrl,
        productPrice = "₹${productResponse.modifiedPrice}",
        quantity = quantity
    )
}

fun Address2.toAddressUiState(): AddressUiState {
    return AddressUiState(
        locality = locality,
        landmark = landmark,
        state = state,
        zipCode = zipCode,
        addressType = addressType,
        phoneNumber = phoneNumber
    )
}