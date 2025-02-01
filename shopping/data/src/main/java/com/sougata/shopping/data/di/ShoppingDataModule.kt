package com.sougata.shopping.data.di

import com.sougata.shopping.data.dataSource.KtorRemoteDataSource
import com.sougata.shopping.data.repository.ShopRepositoryImpl
import com.sougata.shopping.domain.dataSource.ShopRemoteDataSource
import com.sougata.shopping.domain.repository.ShopRepository
import org.koin.dsl.module

val ShoppingDataModule = module{
    single <ShopRemoteDataSource>{
        KtorRemoteDataSource(get())
    }
    single <ShopRepository>{
        ShopRepositoryImpl(get(), get(), get())
    }
}