package com.sougata.shopping.database.mappers

import com.sougata.shopping.database.entity.FilteredProductEntity
import com.sougata.shopping.domain.models.Product

fun FilteredProductEntity.toProduct(): Product {
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


fun Product.toFilteredProductEntity(): FilteredProductEntity {
    return FilteredProductEntity(
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