package com.sougata.shopping.domain.repository

import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.EmptyResult
import com.sougata.core.domain.util.Result
import com.sougata.shopping.domain.models.Address
import com.sougata.shopping.domain.models.AddressResponse
import com.sougata.shopping.domain.models.FetchOrderResponse
import com.sougata.shopping.domain.models.PaymentResponse
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
    suspend fun clearCart(): EmptyResult<DataError.Local>
    suspend fun addToCart(product:ProductCart) : EmptyResult<DataError>
    suspend fun deleteWholeProductFromCart(productId:String) : EmptyResult<DataError>
    suspend fun removeOneProductFromCart(productId:String) : EmptyResult<DataError>
    suspend fun saveAllCategories(): EmptyResult<DataError>
    fun getAllCategories(): Flow<List<ProductCategory>>

    suspend fun fetchAllAddresses(): EmptyResult<DataError.Network>

    suspend fun initialisePayment(addressId:Int): Result<PaymentResponse, DataError.Network>
    suspend fun clearFilterItems()

    fun getAllAddress(): Flow<List<AddressResponse>>
    suspend fun addAddress(address: Address): EmptyResult<DataError>
    suspend fun deleteAddress(addressId: String): EmptyResult<DataError>

    suspend fun fetchAllFilteredProducts(
        pageNo: Int,
        pageSize: Int,
        category: String,
        sortBy: String,
        sortDir: String,
        lowerPriceBound: Int,
        upperPriceBound: Int
        ): EmptyResult<DataError>

    suspend fun getAllFilteredProducts(order: String): Flow<List<Product>>


    suspend fun getAllOrders(): Result<FetchOrderResponse, DataError.Network>
}