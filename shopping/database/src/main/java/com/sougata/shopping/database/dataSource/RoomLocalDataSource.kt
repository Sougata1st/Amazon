package com.sougata.shopping.database.dataSource

import android.database.sqlite.SQLiteException
import com.sougata.core.domain.util.DataError
import com.sougata.core.domain.util.Result
import com.sougata.shopping.database.dao.ProductDao
import com.sougata.shopping.database.entity.ProductCategoryEntity
import com.sougata.shopping.database.entity.ProductEntryEntity
import com.sougata.shopping.database.mappers.toAddressEntity
import com.sougata.shopping.database.mappers.toAddressResponse
import com.sougata.shopping.database.mappers.toFilteredProductEntity
import com.sougata.shopping.database.mappers.toProduct
import com.sougata.shopping.database.mappers.toProductEntity
import com.sougata.shopping.domain.dataSource.ShopLocalDataSource
import com.sougata.shopping.domain.models.AddressResponse
import com.sougata.shopping.domain.models.Product
import com.sougata.shopping.domain.models.ProductCart
import com.sougata.shopping.domain.models.ProductCategory
import com.sougata.shopping.domain.models.ProductEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomLocalDataSource(
    private val dao: ProductDao
): ShopLocalDataSource {
    override fun getAllProducts(): Flow<List<Product>> {
        val result = dao.getAllProducts()
        return result.map { pdtEntityList ->
            pdtEntityList.map { pdtEntity ->
                pdtEntity.toProduct()
            }
        }
    }

    override suspend fun deleteAllProducts() {
        dao.deleteAllProducts()
    }

    override suspend fun upsertProducts(products: List<Product>): Result<List<Product>, DataError.Local> {
        return try {
            dao.upsertAllProducts(products.map {
                it.toProductEntity()
            })
            Result.Success(products)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun getCart(): Flow<List<ProductEntry>> {
        val result = dao.getCart()

        return result.map {
            it.map { productEntryEntity ->
                ProductEntry(
                    product = ProductCart(
                        productId = productEntryEntity.productId,
                        productName = productEntryEntity.productName,
                        basePrice = productEntryEntity.basePrice,
                        stocks = productEntryEntity.stocks,
                        imageUrl = productEntryEntity.imageUrl,
                        category = productEntryEntity.category,
                        modifiedPrice = productEntryEntity.modifiedPrice,
                        unavaliable = productEntryEntity.unavaliable
                    ),
                    quantity = productEntryEntity.quantity
                )
            }
        }
    }

    override suspend fun upsertCarts(products: List<ProductEntry>): Result<List<ProductEntry>, DataError.Local> {
       return try {
           dao.upsertCarts(products.map { productEntry ->
               ProductEntryEntity(
                   productId = productEntry.product.productId,
                   productName = productEntry.product.productName,
                   basePrice = productEntry.product.basePrice,
                   stocks = productEntry.product.stocks,
                   imageUrl = productEntry.product.imageUrl,
                   category = productEntry.product.category,
                   modifiedPrice = productEntry.product.modifiedPrice,
                   unavaliable = productEntry.product.unavaliable,
                   quantity = productEntry.quantity
               )
           })
           Result.Success(products)
       }catch (e: SQLiteException){
           Result.Error(DataError.Local.DISK_FULL)
       }
    }

    override suspend fun insertProductCart(productEntry: ProductEntry) {
        dao.addProduct(
            ProductEntryEntity(
                productId = productEntry.product.productId,
                productName = productEntry.product.productName,
                basePrice = productEntry.product.basePrice,
                stocks = productEntry.product.stocks,
                imageUrl = productEntry.product.imageUrl,
                category = productEntry.product.category,
                modifiedPrice = productEntry.product.modifiedPrice,
                unavaliable = productEntry.product.unavaliable,
                quantity = productEntry.quantity
            )
        )
    }

    override suspend fun deleteWholeProductFromCart(productId: String) {
        dao.deleteProduct(productId)
    }

    override suspend fun removeOneProductFromCart(productId: String) {
        dao.removeProduct(
            productId = productId
        )
    }

    override fun getAllCategories(): Flow<List<ProductCategory>> {
        return dao.getAllCategories().map {
            it.map { productCategoryEntity ->
                ProductCategory(
                    id = productCategoryEntity.id!!,
                    name = productCategoryEntity.name
                )
            }
        }
    }

    override suspend fun saveAllCategories(productCategoryList: List<ProductCategory>): Result<List<ProductCategory>, DataError.Local> {
        return try {
            dao.clearAndInsert(
                productCategoryList.map {
                    ProductCategoryEntity(
                        id = it.id,
                        name = it.name
                    )
                }
            )
            Result.Success(productCategoryList)
        }catch (e: SQLiteException){
            return Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun upsertFilteredProducts(products: List<Product>): Result<List<Product>, DataError.Local> {
        return try {
            dao.insertFilteredProducts(products.map {
                it.toFilteredProductEntity()
            })
            Result.Success(products)
        }catch (e: SQLiteException){
            return Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override fun getAllAddress(): Flow<List<AddressResponse>> {
        return dao.getAllAddresses().map {
            it.map {
                it.toAddressResponse()
            }
        }
    }

    override suspend fun addAddress(address: AddressResponse): Result<AddressResponse, DataError.Local> {
        return try {
            dao.addAddress(address.toAddressEntity())
            Result.Success(address)
        }catch (e: SQLiteException){
            return Result.Error(DataError.Local.DISK_FULL)
        }
    }


    override suspend fun deleteAddress(addressId: Int) {
        dao.deleteAddress(addressId)
    }

    override fun getFilteredProducts(order : String): Flow<List<Product>> {
        return if (order.equals("ASC", ignoreCase = true)) {
            dao.getFilteredProductsAsc()
                .map {
                    it.map {
                        it.toProduct()
                    }
                }
        } else {
            dao.getFilteredProductsDesc()
                .map {
                    it.map {
                        it.toProduct()
                    }
                }
        }
    }

    override suspend fun clearFilterItems() {
        dao.clearAllProducts()
    }
}