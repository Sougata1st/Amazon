package com.sougata.shopping.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FilteredProductEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val productId: String,
    val productName: String,
    val basePrice: Double,
    val stocks: Int,
    val imageUrl: String,
    val category: String,
    val modifiedPrice: Double,
    val unavaliable: Boolean,
    val pageCount: Int
)