package com.sougata.core.domain

data class AuthInfo(
    val refreshToken: String,
    val accessToken : String
)
