package com.sougata.core.data.networking.di

import com.sougata.core.data.networking.HttpClientFactory
import org.koin.dsl.module

val CoreDataModule =  module{
    single {
        HttpClientFactory(get()).build()
    }
}