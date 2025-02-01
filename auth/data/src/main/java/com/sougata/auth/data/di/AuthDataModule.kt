package com.sougata.auth.data.di

import com.sougata.auth.data.AuthRepositoryImpl
import com.sougata.auth.domain.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val AuthDataModule = module {
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}