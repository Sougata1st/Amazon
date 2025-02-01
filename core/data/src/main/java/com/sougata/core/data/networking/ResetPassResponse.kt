package com.sougata.core.data.networking

import kotlinx.serialization.Serializable

@Serializable
data class ResetPassResponse (
    val data: String?,
    val status: Int,
    val success: Boolean,
    val error: ErrorDetails?,
    val timeStamp: String
)