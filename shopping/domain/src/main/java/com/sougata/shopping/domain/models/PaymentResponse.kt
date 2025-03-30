package com.sougata.shopping.domain.models

data class PaymentResponse(
    val data: PaymentData,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)


data class PaymentData(
    val orderId: String,
    val key: String,
    val currency: String,
    val amount: Int
)
