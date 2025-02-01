package com.sougata.core.data.auth.di

import com.sougata.core.data.auth.EncryptedSessionStorage
import com.sougata.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val AuthModule = module{
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}