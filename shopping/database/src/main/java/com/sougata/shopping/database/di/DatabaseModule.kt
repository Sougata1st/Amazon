package com.sougata.shopping.database.di

import androidx.room.Room
import com.sougata.shopping.database.ProductDatabase
import com.sougata.shopping.database.dataSource.RoomLocalDataSource
import com.sougata.shopping.domain.dataSource.ShopLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val DatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ProductDatabase::class.java,
            ProductDatabase.DB_NAME
        ) .fallbackToDestructiveMigration().build()
    }

    single {
        get<ProductDatabase>().productDao
    }

    single <ShopLocalDataSource>{
        RoomLocalDataSource(get())
    }

}