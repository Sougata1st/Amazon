package com.sougata.amazon.networking

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val loggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d("RetrofitLog", message) // Logs using Android's Logcat
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }


    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://e-store-order-service.onrender.com")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val paymentApiService: PaymentApiService = retrofit.create(PaymentApiService::class.java)
}