package com.sougata.shopping.domain.models


data class FetchOrderResponse(
    val data: OrderData,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)

data class OrderData(
    val content: List<Order>,
    val currentPage: Int,
    val pageSize: Int,
    val totalItems: Int,
    val currentPageItemsNumber: Int,
    val totalPages: Int,
    val lastPage: Boolean
)

data class Order(
    val id: Int,
    val razorpayOrderId: String,
    val grandTotal: Double,
    val finalPrice: Double,
    val userId: Int,
    val createdAt: String,
    val transactionId: String,
    val status: String,
    val products: List<ProductOrder>,
    val address: Address2
)

data class ProductOrder(
    val productResponse: ProductResponse,
    val quantity: Int,
    val orderId: Int
)

data class ProductResponse(
    val productId: String,
    val productName: String,
    val basePrice: Double,
    val stocks: Int,
    val imageUrl: String,
    val category: String,
    val modifiedPrice: Double,
    val unavaliable: Boolean
)

data class Address2(
    val locality: String,
    val landmark: String,
    val state: String,
    val zipCode: String,
    val addressType: String,
    val phoneNumber: String
)