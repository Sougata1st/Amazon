package com.sougata.shopping.domain.dataSource

import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.Result
import com.sougata.shopping.domain.models.AddToCartResponse
import com.sougata.shopping.domain.models.Address
import com.sougata.shopping.domain.models.AddressResponse
import com.sougata.shopping.domain.models.DeleteAddressResponse
import com.sougata.shopping.domain.models.FetchOrderResponse
import com.sougata.shopping.domain.models.PaymentResponse
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

    suspend fun addAddress(address: Address) : Result<AddressResponse, DataError.Network>

    suspend fun deleteAddress(addressId : String) : Result<DeleteAddressResponse, DataError.Network>

    suspend fun getAllAddress() : Result<List<AddressResponse>, DataError.Network>

    suspend fun initiatePayment(addressId:Int): Result<PaymentResponse, DataError.Network>

    suspend fun getAllFilteredProducts(
        pageNo: Int, pageSize: Int, category: String,
        sortBy: String,
        sortDirection: String,
        lowerPriceBound: Int,
        upperPriceBound: Int
    ): Result<List<Product>, DataError.Network>

    suspend fun getAllOrders(): Result<FetchOrderResponse, DataError.Network>
}