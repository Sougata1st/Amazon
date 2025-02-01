package com.sougata.core.data.networking

import kotlinx.serialization.Serializable

@Serializable
data class ResetPassRequest(
    val email: String,
    val password: String
)
