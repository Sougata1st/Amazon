package com.sougata.shopping.presentation.di

import com.sougata.shopping.presentation.filter.FilterViewModel
import com.sougata.shopping.presentation.filterResult.FilterResultScreenViewModel
import com.sougata.shopping.presentation.homeRoot.cart.CartScreenViewModel
import com.sougata.shopping.presentation.homeRoot.home.HomeScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ShoppingViewModelModule = module {
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::CartScreenViewModel)
    viewModelOf(::FilterViewModel)
    viewModelOf(::FilterResultScreenViewModel)
}