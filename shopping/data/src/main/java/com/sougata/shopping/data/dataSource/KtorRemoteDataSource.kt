package com.sougata.shopping.data.dataSource

import com.sougata.core.data.networking.delete
import com.sougata.core.data.networking.get
import com.sougata.core.data.networking.post
import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.Result
import com.sougata.core.domain.util.map
import com.sougata.shopping.data.dto.AddAddressRequestDto
import com.sougata.shopping.data.dto.AddAddressResponseDTO
import com.sougata.shopping.data.dto.AddToCartSerializable
import com.sougata.shopping.data.dto.DeleteAddressResponseDto
import com.sougata.shopping.data.dto.FetchOrderResponseDto
import com.sougata.shopping.data.dto.GetAllAddressResponseDto
import com.sougata.shopping.data.dto.PaymentResponseDto
import com.sougata.shopping.data.dto.ProductCategoryResponseSerializable
import com.sougata.shopping.data.dto.ProductResponse
import com.sougata.shopping.data.dto.ResponseDto
import com.sougata.shopping.data.dto.toAddAddressRequestDtoSerializable
import com.sougata.shopping.data.dto.toAddToCartResponse
import com.sougata.shopping.data.dto.toAddressResponse
import com.sougata.shopping.data.dto.toDeleteAddressResponse
import com.sougata.shopping.data.dto.toDomain
import com.sougata.shopping.data.dto.toPaymentResponse
import com.sougata.shopping.domain.dataSource.ShopRemoteDataSource
import com.sougata.shopping.domain.models.AddToCartResponse
import com.sougata.shopping.domain.models.Address
import com.sougata.shopping.domain.models.AddressResponse
import com.sougata.shopping.domain.models.DeleteAddressResponse
import com.sougata.shopping.domain.models.FetchOrderResponse
import com.sougata.shopping.domain.models.PaymentResponse
import com.sougata.shopping.domain.models.Product
import com.sougata.shopping.domain.models.ProductCart
import com.sougata.shopping.domain.models.ProductCategory
import com.sougata.shopping.domain.models.ProductCategoryResponse
import com.sougata.shopping.domain.models.ProductEntry
import io.ktor.client.HttpClient

class KtorRemoteDataSource(
    private val httpClient: HttpClient
) : ShopRemoteDataSource {
    override suspend fun getAllProducts(
        pageNo: Int,
        pageSize: Int
    ): Result<List<Product>, DataError.Network> {
        val result = httpClient.get<ProductResponse>(
            route = "/products",
            queryParameters = mapOf(
                "pageNo" to pageNo,
                "pageSize" to pageSize
            ),
            baseUrl = "https://e-comm-inventory-service.onrender.com"
        )

        return result.map { productResponse ->
            productResponse.data.content.map {
                Product(
                    id = it.id,
                    productId = it.productId,
                    productName = it.productName,
                    basePrice = it.basePrice,
                    stocks = it.stocks,
                    imageUrl = it.imageUrl,
                    category = it.category,
                    modifiedPrice = it.modifiedPrice,
                    unavaliable = it.unavaliable,
                    pageCount = productResponse.data.totalPages
                )
            }

        }
    }

    override suspend fun getCart(): Result<List<ProductEntry>, DataError.Network> {
        val result = httpClient.get<ResponseDto>(
            route = "/cart/get-cart",
            baseUrl = "https://e-store-order-service.onrender.com"
        )
        return result.map {
            it.data.products.map { productEntryDto ->
                ProductEntry(
                    product = ProductCart(
                        productId = productEntryDto.product.productId,
                        productName = productEntryDto.product.productName,
                        basePrice = productEntryDto.product.basePrice,
                        stocks = productEntryDto.product.stocks,
                        imageUrl = productEntryDto.product.imageUrl,
                        category = productEntryDto.product.category,
                        modifiedPrice = productEntryDto.product.modifiedPrice,
                        unavaliable = productEntryDto.product.unavaliable
                    ),
                    quantity = productEntryDto.quantity
                )
            }
        }
    }

    override suspend fun addToCart(productId: String): Result<AddToCartResponse, DataError.Network> {
        val result = httpClient.post<Unit, AddToCartSerializable>(
            route = "/cart/add?",
            baseUrl = "https://e-store-order-service.onrender.com",
            queryParameters = mapOf(
                "productId" to productId
            ),
            body = Unit
        )
        return result.map {
            it.toAddToCartResponse()
        }
    }

    override suspend fun removeOneByOneFromCart(productId: String): Result<AddToCartResponse, DataError.Network> {
        val result = httpClient.delete<AddToCartSerializable>(
            route = "/cart/remove",
            baseUrl = "https://e-store-order-service.onrender.com",
            queryParameters = mapOf(
                "productId" to productId
            )
        )
        return result.map {
            it.toAddToCartResponse()
        }
    }

    override suspend fun removeWholeProductFromCart(productId: String): Result<AddToCartResponse, DataError.Network> {
        val result = httpClient.delete<AddToCartSerializable>(
            route = "/cart/remove-whole",
            baseUrl = "https://e-store-order-service.onrender.com",
            queryParameters = mapOf(
                "productId" to productId
            )
        )
        return result.map {
            it.toAddToCartResponse()
        }
    }

    override suspend fun getAllCategories(): Result<ProductCategoryResponse, DataError.Network> {
        val result = httpClient.get<ProductCategoryResponseSerializable>(
            baseUrl = "https://e-comm-inventory-service.onrender.com",
            route = "/category"
        )
        return result.map {
            ProductCategoryResponse(
                data = it.data.map { productCategorySerializable ->
                    ProductCategory(
                        id = productCategorySerializable.id,
                        name = productCategorySerializable.name
                    )
                },
                status = it.status,
                success = it.success,
                error = it.error,
                timeStamp = it.timeStamp
            )
        }
    }

    override suspend fun addAddress(address: Address): Result<AddressResponse, DataError.Network> {
        val result = httpClient.post<AddAddressRequestDto, AddAddressResponseDTO>(
            baseUrl = "https://e-store-user-service.onrender.com",
            route = "/address",
            body = address.toAddAddressRequestDtoSerializable(),
        )

        return result.map {
            it.toAddressResponse()
        }
    }

    override suspend fun deleteAddress(addressId: String): Result<DeleteAddressResponse, DataError.Network> {
        val response = httpClient.delete<DeleteAddressResponseDto>(
            baseUrl = "https://e-store-user-service.onrender.com",
            route = "/address/delete",
            queryParameters = mapOf(
                "id" to addressId
            )
        )

        return response.map {
            it.toDeleteAddressResponse()
        }
    }

    override suspend fun getAllAddress(): Result<List<AddressResponse>, DataError.Network> {
        val response = httpClient.get<GetAllAddressResponseDto>(
            baseUrl = "https://e-store-user-service.onrender.com",
            route = "/address"
        )
        return response.map { getAllAddressResponseDto ->
            getAllAddressResponseDto.data.map { addressDTO ->
                addressDTO.toAddressResponse()
            }
        }
    }

    override suspend fun initiatePayment(addressId:Int): Result<PaymentResponse, DataError.Network> {
        val response = httpClient.post<Unit,PaymentResponseDto>(
            baseUrl = "https://e-store-order-service.onrender.com",
            route = "/order/place-order",
            queryParameters = mapOf(
                "address" to addressId
            ),
            body = Unit
        )
        return response.map {
            it.toPaymentResponse()
        }
    }

    override suspend fun getAllFilteredProducts(
        pageNo: Int,
        pageSize: Int,
        category: String,
        sortBy: String,
        sortDirection: String,
        lowerPriceBound: Int,
        upperPriceBound: Int
    ): Result<List<Product>, DataError.Network> {
        val result = httpClient.get<ProductResponse>(
            baseUrl = "https://e-comm-inventory-service.onrender.com",
            route = "/products/filter",
            queryParameters = mapOf(
                "pageNo" to pageNo,
                "pageSize" to pageSize,
                "category" to category,
                "sortBy" to sortBy,
                "sortDir" to sortDirection,
                "lowerPriceBound" to lowerPriceBound,
                "upperPriceBound" to upperPriceBound
            )
        )
        return result.map { productResponse ->
            productResponse.data.content.map {
                Product(
                    id = it.id,
                    productId = it.productId,
                    productName = it.productName,
                    basePrice = it.basePrice,
                    stocks = it.stocks,
                    imageUrl = it.imageUrl,
                    category = it.category,
                    modifiedPrice = it.modifiedPrice,
                    unavaliable = it.unavaliable,
                    pageCount = productResponse.data.totalPages
                )
            }

        }
    }

    override suspend fun getAllOrders(): Result<FetchOrderResponse, DataError.Network> {
        val result = httpClient.get<FetchOrderResponseDto>(
            baseUrl = "https://e-store-order-service.onrender.com",
            route = "/order/user-order?",
            queryParameters = mapOf(
                "pageNo" to 0,
                "pageSize" to 50,
                "sortBy" to "createdAt",
                "sortDir" to "DESC"
            )
        )

        return result.map {
            it.toDomain()
        }
    }
}