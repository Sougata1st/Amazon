package com.sougata.core.data.networking

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpResponse (
    val data: String? = null,
    val status: Int,
    val success: Boolean,
    val error: ErrorDetails?,
    val timeStamp: String
)
@Serializable
data class ErrorDetails(
    val error: String,
    val suberrors: String? = null
)