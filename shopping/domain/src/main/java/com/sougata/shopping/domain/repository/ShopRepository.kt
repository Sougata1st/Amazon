package com.sougata.shopping.domain.repository

import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.EmptyResult
import com.sougata.shopping.domain.models.Product
import com.sougata.shopping.domain.models.ProductCart
import com.sougata.shopping.domain.models.ProductCategory
import com.sougata.shopping.domain.models.ProductEntry
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
    suspend fun fetchAllCarts(): EmptyResult<DataError>
    fun getAllProducts(): Flow<List<Product>>
    suspend fun deleteAllProducts()
    suspend fun fetchAllProducts(pageNo:Int, pageSize:Int) : EmptyResult<DataError>
    fun getCart(): Flow<List<ProductEntry>>
    suspend fun addToCart(product:ProductCart) : EmptyResult<DataError>
    suspend fun deleteWholeProductFromCart(productId:String) : EmptyResult<DataError>
    suspend fun removeOneProductFromCart(productId:String) : EmptyResult<DataError>
    suspend fun saveAllCategories(): EmptyResult<DataError>
    fun getAllCategories(): Flow<List<ProductCategory>>

    suspend fun clearFilterItems()


    suspend fun fetchAllFilteredProducts(
        pageNo: Int,
        pageSize: Int,
        category: String,
        sortBy: String,
        sortDir: String,
        lowerPriceBound: Int,
        upperPriceBound: Int
        ): EmptyResult<DataError>

    suspend fun getAllFilteredProducts(): Flow<List<Product>>
}