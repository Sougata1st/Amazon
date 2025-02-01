package com.sougata.auth.presentation.di

import com.sougata.auth.presentation.forgot_pass.change_pass.ChangePassViewModel
import com.sougata.auth.presentation.forgot_pass.send_and_verify_otp.SendOtpViewModel
import com.sougata.auth.presentation.login.LoginViewModel
import com.sougata.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val AuthViewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::SendOtpViewModel)
    viewModelOf(::ChangePassViewModel)
}