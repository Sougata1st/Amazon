package com.sougata.shopping.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sougata.shopping.database.dao.ProductDao
import com.sougata.shopping.database.entity.AddressEntity
import com.sougata.shopping.database.entity.FilteredProductEntity
import com.sougata.shopping.database.entity.ProductCategoryEntity
import com.sougata.shopping.database.entity.ProductEntity
import com.sougata.shopping.database.entity.ProductEntryEntity

@Database(
    entities = [ProductEntity::class, ProductEntryEntity::class,
        ProductCategoryEntity::class, FilteredProductEntity::class,
        AddressEntity::class],
    version = 8
)
abstract class ProductDatabase : RoomDatabase() {
    abstract val productDao: ProductDao

    companion object {
        const val DB_NAME = "amazon_db"
    }
}