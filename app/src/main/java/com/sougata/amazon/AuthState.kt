package com.sougata.amazon

data class AuthState(
    val isLoggedIn: Boolean = false,
    val isCheckingAuth: Boolean = false
)