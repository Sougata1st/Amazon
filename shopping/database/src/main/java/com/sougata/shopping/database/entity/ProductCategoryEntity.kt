package com.sougata.shopping.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductCategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val name: String
)
