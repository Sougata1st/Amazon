package com.sougata.core.data.networking

import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequest (
    val email: String,
    val otp: String
)