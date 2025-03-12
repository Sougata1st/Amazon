package com.sougata.shopping.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.sougata.shopping.database.entity.FilteredProductEntity
import com.sougata.shopping.database.entity.ProductCategoryEntity
import com.sougata.shopping.database.entity.ProductEntity
import com.sougata.shopping.database.entity.ProductEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductEntity")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("Delete FROM ProductEntity")
    suspend fun deleteAllProducts()

    @Upsert
    suspend fun insertAllProducts(productEntities: List<ProductEntity>)

    @Transaction
    suspend fun upsertAllProducts(productEntities: List<ProductEntity>){
        //deleteAllProducts()
        insertAllProducts(productEntities)
    }

    @Query("SELECT * FROM ProductEntryEntity")
    fun getCart(): Flow<List<ProductEntryEntity>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: ProductEntryEntity)

    @Query("""
        UPDATE ProductEntryEntity 
        SET quantity = quantity + 1 
        WHERE productId = :productId
    """)
    suspend fun incrementQuantity(productId: String): Int

    //adding pdt
    @Transaction
    suspend fun addProduct(product: ProductEntryEntity) {
        val rowsUpdated = incrementQuantity(product.productId)
        if (rowsUpdated == 0) {
            insertProduct(product.copy(quantity = 1))
        }
    }

    @Query("""
        UPDATE ProductEntryEntity 
        SET quantity = quantity - 1 
        WHERE productId = :productId
    """)
    suspend fun decrementQuantity(productId: String): Int

    @Query("""
        DELETE FROM ProductEntryEntity 
        WHERE productId = :productId
    """)
    suspend fun deleteProduct(productId: String)

    //removing pdt
    @Transaction
    suspend fun removeProduct(productId: String) {
        val rowsUpdated = decrementQuantity(productId)
        if (rowsUpdated == 0) {
            deleteProduct(productId)
        } else {
            val product = getProductById(productId)
            if (product != null && product.quantity == 0) {
                deleteProduct(productId)
            }
        }
    }

    @Transaction
    suspend fun upsertCarts(products: List<ProductEntryEntity>) {
        products.forEach { product ->
            val rowsUpdated = incrementQuantity(product.productId) // Try to increment quantity
            if (rowsUpdated == 0) {
                // If the product doesn't exist, insert it with quantity = 1
                insertProduct(product.copy(quantity = 1))
            }
        }
    }

    @Query("""
        SELECT * FROM ProductEntryEntity 
        WHERE productId = :productId
    """)
    suspend fun getProductById(productId: String): ProductEntryEntity?

    @Query("DELETE FROM ProductCategoryEntity")
    suspend fun clearAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<ProductCategoryEntity>)

    // Function to clear and insert
    @Transaction
    suspend fun clearAndInsert(categories: List<ProductCategoryEntity>) {
        clearAll()
        insertAll(categories)
    }

    @Query("SELECT * FROM ProductCategoryEntity")
    fun getAllCategories(): Flow<List<ProductCategoryEntity>>

    @Query("SELECT * FROM FilteredProductEntity")
    fun getFilteredProducts(): Flow<List<FilteredProductEntity>>

    @Query("SELECT * FROM FilteredProductEntity ORDER BY basePrice ASC")
    fun getFilteredProductsAsc(): Flow<List<FilteredProductEntity>>

    @Query("SELECT * FROM FilteredProductEntity ORDER BY basePrice DESC")
    fun getFilteredProductsDesc(): Flow<List<FilteredProductEntity>>

    @Query("DELETE FROM FilteredProductEntity")
    suspend fun clearAllProducts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFilteredProducts(products: List<FilteredProductEntity>)

    @Transaction
    suspend fun insertFilteredProducts(products: List<FilteredProductEntity>) {
        //clearAllProducts()
        insertAllFilteredProducts(products)
    }
}