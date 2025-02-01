package com.sougata.shopping.domain.dataSource

import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.Result
import com.sougata.shopping.domain.models.Product
import com.sougata.shopping.domain.models.ProductCategory
import com.sougata.shopping.domain.models.ProductEntry
import kotlinx.coroutines.flow.Flow

interface ShopLocalDataSource {
    fun getAllProducts(): Flow<List<Product>>
    suspend fun deleteAllProducts()
    suspend fun upsertProducts(products: List<Product>) : Result<List<Product>, DataError.Local>
    fun getCart(): Flow<List<ProductEntry>>
    suspend fun upsertCarts(products: List<ProductEntry>) : Result<List<ProductEntry>, DataError.Local>
    suspend fun insertProductCart(productEntry: ProductEntry)
    suspend fun deleteWholeProductFromCart(productId:String)
    suspend fun removeOneProductFromCart(productId:String)
    fun getAllCategories(): Flow<List<ProductCategory>>
    suspend fun saveAllCategories(productCategoryList: List<ProductCategory>) : Result<List<ProductCategory>, DataError.Local>
    suspend fun upsertFilteredProducts(products: List<Product>) : Result<List<Product>, DataError.Local>
    fun getFilteredProducts(): Flow<List<Product>>
    suspend fun clearFilterItems()

}