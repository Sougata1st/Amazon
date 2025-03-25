package com.sougata.shopping.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressEntity(
    val locality: String,
    val landmark: String,
    val state: String,
    val zipCode: String,
    val addressType: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val phoneNumber: String
)

