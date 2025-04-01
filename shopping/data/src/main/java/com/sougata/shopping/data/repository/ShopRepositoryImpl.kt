package com.sougata.shopping.data.repository

import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.EmptyResult
import com.sougata.core.domain.util.Result
import com.sougata.core.domain.util.asEmptyDataResult
import com.sougata.shopping.domain.dataSource.ShopLocalDataSource
import com.sougata.shopping.domain.dataSource.ShopRemoteDataSource
import com.sougata.shopping.domain.models.Address
import com.sougata.shopping.domain.models.AddressResponse
import com.sougata.shopping.domain.models.FetchOrderResponse
import com.sougata.shopping.domain.models.PaymentResponse
import com.sougata.shopping.domain.models.Product
import com.sougata.shopping.domain.models.ProductCart
import com.sougata.shopping.domain.models.ProductCategory
import com.sougata.shopping.domain.models.ProductEntry
import com.sougata.shopping.domain.repository.ShopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ShopRepositoryImpl(
    private val remoteDataSource: ShopRemoteDataSource,
    private val localDataSource: ShopLocalDataSource,
    private val applicationScope: CoroutineScope
) : ShopRepository {
    override suspend fun fetchAllCarts(): EmptyResult<DataError> {
        val result =applicationScope.async {
            remoteDataSource.getCart()
        }.await()
        when(result){
            is Result.Error -> {
                return result.asEmptyDataResult()
            }
            is Result.Success -> {
                applicationScope.launch {
                    localDataSource.upsertCarts(result.data)
                }.join()
                return Result.Success(Unit).asEmptyDataResult()
            }
        }
    }

    override fun getAllProducts(): Flow<List<Product>> {
        return localDataSource.getAllProducts()
    }

    override suspend fun deleteAllProducts() {
        applicationScope.launch {
            localDataSource.deleteAllProducts()
        }.join()
    }

    override suspend fun fetchAllProducts(pageNo: Int, pageSize: Int): EmptyResult<DataError> {
        val result = applicationScope.async {
            remoteDataSource.getAllProducts(
                pageNo = pageNo,
                pageSize = pageSize
            )
        }.await()



        return when (result) {
            is Result.Error -> {
                result.asEmptyDataResult()
            }

            is Result.Success -> {
                applicationScope.async {
                    localDataSource.upsertProducts(result.data).asEmptyDataResult()
                }.await()
            }
        }
    }

    override fun getCart(): Flow<List<ProductEntry>> {
        return localDataSource.getCart()
    }

    override suspend fun clearCart(): EmptyResult<DataError.Local> {
        return  applicationScope.async {
            localDataSource.clearCart()
        }.await()
    }

    override suspend fun addToCart(product: ProductCart): EmptyResult<DataError> {
        val result =
            applicationScope.async { remoteDataSource.addToCart(product.productId) }.await()
        return when (result) {
            is Result.Error -> result.asEmptyDataResult()
            is Result.Success -> {

                applicationScope.async {
                    localDataSource.insertProductCart(
                        ProductEntry(
                            product = ProductCart(
                                productId = product.productId,
                                productName = product.productName,
                                basePrice = product.basePrice,
                                stocks = product.stocks,
                                imageUrl = product.imageUrl,
                                category = product.category,
                                modifiedPrice = product.modifiedPrice,
                                unavaliable = product.unavaliable
                            ),
                            quantity = 0
                        )
                    )
                }.await()

                Result.Success(Unit).asEmptyDataResult()
            }
        }
    }

    override suspend fun deleteWholeProductFromCart(productId: String): EmptyResult<DataError> {
        val result = applicationScope.async { remoteDataSource.removeWholeProductFromCart(productId) }.await()

        return when(result){
            is Result.Error -> result.asEmptyDataResult()
            is Result.Success -> {
                applicationScope.async {
                    localDataSource.deleteWholeProductFromCart(productId)
                }.await()
                Result.Success(Unit).asEmptyDataResult()
            }
        }
    }

    override suspend fun removeOneProductFromCart(productId: String): EmptyResult<DataError> {
        val result = applicationScope.async { remoteDataSource.removeOneByOneFromCart(productId)}.await()
        return when(result){
            is Result.Error -> {
                result.asEmptyDataResult()
            }
            is Result.Success -> {
                applicationScope.async {
                    localDataSource.removeOneProductFromCart(productId)
                }.await()
                Result.Success(Unit).asEmptyDataResult()
            }
        }
    }

    override suspend fun saveAllCategories(): EmptyResult<DataError> {
        val result = applicationScope.async {remoteDataSource.getAllCategories()}.await()
        return when(result){
            is Result.Error -> result.asEmptyDataResult()
            is Result.Success -> {
                val data = result.data.data
                applicationScope.async {
                    localDataSource.saveAllCategories(data)
                }.await()
                Result.Success(Unit).asEmptyDataResult()
            }
        }
    }

    override fun getAllCategories(): Flow<List<ProductCategory>> {
        return localDataSource.getAllCategories()
    }

    override suspend fun fetchAllAddresses(): EmptyResult<DataError.Network> {
        val result = applicationScope.async { remoteDataSource.getAllAddress() }.await()
        return when(result){
            is Result.Error -> result.asEmptyDataResult()
            is Result.Success -> {
                applicationScope.async { localDataSource.addAllAddresses(result.data) }.await()
                result.asEmptyDataResult()
            }
        }
    }

    override suspend fun initialisePayment(addressId:Int): Result<PaymentResponse, DataError.Network> {
        return remoteDataSource.initiatePayment(addressId)
    }

    override suspend fun clearFilterItems() {
       localDataSource.clearFilterItems()
    }

    override fun getAllAddress(): Flow<List<AddressResponse>> {
        return localDataSource.getAllAddress()
    }

    override suspend fun addAddress(address: Address): EmptyResult<DataError> {
        val result = applicationScope.async { remoteDataSource.addAddress(address)}.await()
        return when(result){
            is Result.Error -> {
                result.asEmptyDataResult()
            }
            is Result.Success -> {
                applicationScope.async {
                    localDataSource.addAddress(result.data)
                }.await().asEmptyDataResult()
            }
        }
    }

    override suspend fun deleteAddress(addressId: String): EmptyResult<DataError> {
        val result = applicationScope.async { remoteDataSource.deleteAddress(addressId) }.await()
        return when(result){
            is Result.Error -> {
                result.asEmptyDataResult()
            }
            is Result.Success -> {
                applicationScope.async { localDataSource.deleteAddress(addressId.toInt()) }.await()
                Result.Success(Unit)
            }
        }
    }

    override suspend fun fetchAllFilteredProducts(
        pageNo: Int,
        pageSize: Int,
        category: String,
        sortBy: String,
        sortDir: String,
        lowerPriceBound: Int,
        upperPriceBound: Int
    ): EmptyResult<DataError> {
        val result = applicationScope.async {
            remoteDataSource.getAllFilteredProducts(pageNo,pageSize,category,sortBy,sortDir,lowerPriceBound, upperPriceBound)
        }.await()
        return when(result){
            is Result.Error -> {
                result.asEmptyDataResult()
            }
            is Result.Success -> {
                applicationScope.async {
                    localDataSource.upsertFilteredProducts(
                        result.data
                    )
                }.await()
                Result.Success(Unit).asEmptyDataResult()
            }
        }
    }

    override suspend fun getAllFilteredProducts(order:String): Flow<List<Product>> {
        return applicationScope.async{ localDataSource.getFilteredProducts(order) }.await()
    }


    override suspend fun getAllOrders(): Result<FetchOrderResponse, DataError.Network> {
        return applicationScope.async { remoteDataSource.getAllOrders() }.await()
    }

}