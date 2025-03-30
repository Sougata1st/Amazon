package com.sougata.shopping.data.dto

import com.sougata.shopping.domain.models.Address2
import com.sougata.shopping.domain.models.FetchOrderResponse
import com.sougata.shopping.domain.models.OrderData
import com.sougata.shopping.domain.models.ProductResponse
import kotlinx.serialization.Serializable


@Serializable
data class FetchOrderResponseDto(
    val data: Data,
    val status: Int,
    val success: Boolean,
    val error: String?,
    val timeStamp: String
)

@Serializable
data class Data(
    val content: List<Order>,
    val currentPage: Int,
    val pageSize: Int,
    val totalItems: Int,
    val currentPageItemsNumber: Int,
    val totalPages: Int,
    val lastPage: Boolean
)

@Serializable
data class Order(
    val id: Int,
    val razorpayOrderId: String,
    val grandTotal: Double,
    val finalPrice: Double,
    val userId: Int,
    val createdAt: String,
    val transactionId: String,
    val status: String,
    val products: List<ProductOrder>,
    val address: AddressDto
)

@Serializable
data class ProductOrder(
    val productResponse: ProductResponse02,
    val quantity: Int,
    val orderId: Int
)

@Serializable
data class ProductResponse02(
    val productId: String,
    val productName: String,
    val basePrice: Double,
    val stocks: Int,
    val imageUrl: String,
    val category: String,
    val modifiedPrice: Double,
    val unavaliable: Boolean
)

@Serializable
data class AddressDto(
    val locality: String,
    val landmark: String,
    val state: String,
    val zipCode: String,
    val addressType: String,
    val phoneNumber: String
)


fun FetchOrderResponseDto.toDomain(): FetchOrderResponse {
    return FetchOrderResponse(
        data = data.toDomain(),
        status = status,
        success = success,
        error = error,
        timeStamp = timeStamp
    )
}

fun Data.toDomain(): OrderData {
    return OrderData(
        content = content.map {
            it.toDomain()
        },
        currentPage = currentPage,
        pageSize = pageSize,
        totalItems = totalItems,
        currentPageItemsNumber = currentPageItemsNumber,
        totalPages = totalPages,
        lastPage = lastPage
    )
}

fun Order.toDomain(): com.sougata.shopping.domain.models.Order {
    return com.sougata.shopping.domain.models.Order(
        id = id,
        razorpayOrderId = razorpayOrderId,
        grandTotal = grandTotal,
        finalPrice = finalPrice,
        userId = userId,
        createdAt = createdAt,
        transactionId = transactionId,
        status = status,
        products = products.map {
            it.toDomain()
        },
        address = address.toDomain()
    )
}

fun ProductOrder.toDomain(): com.sougata.shopping.domain.models.ProductOrder {
    return com.sougata.shopping.domain.models.ProductOrder(
        productResponse = productResponse.toDomain(),
        quantity = quantity,
        orderId = orderId
    )
}

fun ProductResponse02.toDomain(): com.sougata.shopping.domain.models.ProductResponse {
    return ProductResponse(
        productId = productId,
        productName = productName,
        basePrice = basePrice,
        stocks = stocks,
        imageUrl = imageUrl,
        category = category,
        modifiedPrice = modifiedPrice,
        unavaliable = unavaliable
    )
}

fun AddressDto.toDomain(): Address2 {
    return Address2(
        locality = locality,
        landmark = landmark,
        state = state,
        zipCode = zipCode,
        addressType = addressType,
        phoneNumber = phoneNumber
    )
}