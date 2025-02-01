package com.sougata.shopping.database.mappers

import com.sougata.shopping.database.entity.ProductEntity
import com.sougata.shopping.domain.models.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        productId = productId,
        productName = productName,
        basePrice = basePrice,
        stocks = stocks,
        imageUrl = imageUrl,
        category = category,
        modifiedPrice = modifiedPrice,
        unavaliable = unavaliable,
        pageCount = pageCount
    )
}

fun Product.toProductEntity(): ProductEntity{
    return ProductEntity(
        id = id,
        productId = productId,
        productName = productName,
        basePrice = basePrice,
        stocks = stocks,
        imageUrl = imageUrl,
        category = category,
        modifiedPrice = modifiedPrice,
        unavaliable = unavaliable,
        pageCount = pageCount
    )
}