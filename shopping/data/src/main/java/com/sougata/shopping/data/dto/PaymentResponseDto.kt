package com.sougata.shopping.data.dto

import com.sougata.shopping.domain.models.PaymentResponse
import kotlinx.serialization.Serializable

@Serializable
data class PaymentResponseDto(
    val data: PaymentData,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)

@Serializable
data class PaymentData(
    val orderId: String,
    val key: String,
    val currency: String,
    val amount: Int
)

fun PaymentResponseDto.toPaymentResponse(): PaymentResponse {
    return PaymentResponse(
        data = com.sougata.shopping.domain.models.PaymentData(
            orderId = data.orderId,
            key = data.key,
            currency = data.currency,
            amount = data.amount
        ),
        status = status,
        success = success,
        error = error,
        timeStamp = timeStamp
    )
}
