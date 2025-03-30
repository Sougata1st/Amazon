package com.sougata.amazon.networking

import com.google.gson.annotations.SerializedName

data class PaymentRequest(
    @SerializedName("orderId") val orderId: String,
    @SerializedName("txnId") val txnId: String,
    @SerializedName("signature") val signature: String
)

data class PaymentResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("orderId") val orderId: String,
    @SerializedName("transactionId") val transactionId: String,
    @SerializedName("status") val status: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("signature") val signature: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("userId") val userId: Int
)