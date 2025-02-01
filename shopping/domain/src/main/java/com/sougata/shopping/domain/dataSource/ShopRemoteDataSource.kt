package com.sougata.shopping.domain.dataSource

import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.Result
import com.sougata.shopping.domain.models.AddToCartResponse
import com.sougata.shopping.domain.models.Product
import com.sougata.shopping.domain.models.ProductCategoryResponse
import com.sougata.shopping.domain.models.ProductEntry

interface ShopRemoteDataSource {
    suspend fun getAllProducts(pageNo: Int, pageSize: Int): Result<List<Product>, DataError.Network>
    suspend fun getCart(): Result<List<ProductEntry>, DataError.Network>
    suspend fun addToCart(productId: String): Result<AddToCartResponse, DataError.Network>
    suspend fun removeOneByOneFromCart(productId: String): Result<AddToCartResponse, DataError.Network>
    suspend fun removeWholeProductFromCart(productId: String): Result<AddToCartResponse, DataError.Network>
    suspend fun getAllCategories(): Result<ProductCategoryResponse, DataError.Network>

    suspend fun getAllFilteredProducts(
        pageNo: Int, pageSize: Int, category: String,
        sortBy: String,
        sortDirection: String,
        lowerPriceBound: Int,
        upperPriceBound: Int
    ): Result<List<Product>, DataError.Network>
}