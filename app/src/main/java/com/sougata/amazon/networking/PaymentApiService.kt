package com.sougata.amazon.networking

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PaymentApiService {
    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("/order/process-payment")
    suspend fun processPayment(@Body paymentRequest: PaymentRequest): Response<PaymentResponse>
}