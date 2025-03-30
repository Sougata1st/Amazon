package com.sougata.shopping.presentation.homeRoot.cart.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.formatDateTime(): String {
    return try {
        val dateTime = LocalDateTime.parse(this)
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy 'on' HH:mm:ss")
        dateTime.format(formatter)
    } catch (e: Exception) {
        "Invalid date-time format"
    }
}
