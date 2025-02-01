package com.sougata.amazon

import android.app.Application
import com.sougata.amazon.di.AppModule
import com.sougata.auth.data.di.AuthDataModule
import com.sougata.auth.presentation.di.AuthViewModelModule
import com.sougata.core.data.auth.di.AuthModule
import com.sougata.core.data.networking.di.CoreDataModule
import com.sougata.shopping.data.di.ShoppingDataModule
import com.sougata.shopping.database.di.DatabaseModule
import com.sougata.shopping.presentation.di.ShoppingViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AmazonApp: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AmazonApp)
            modules(
                AuthViewModelModule,
                AuthDataModule,
                CoreDataModule,
                AppModule,
                AuthModule,
                DatabaseModule,
                ShoppingDataModule,
                ShoppingViewModelModule
            )
        }
    }
}