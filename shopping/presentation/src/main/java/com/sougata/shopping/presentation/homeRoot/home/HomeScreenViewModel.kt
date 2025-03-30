package com.sougata.shopping.presentation.homeRoot.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.core.domain.util.Result
import com.sougata.core.presentation.ui.UiText
import com.sougata.shopping.domain.models.ProductCart
import com.sougata.shopping.domain.repository.ShopRepository
import com.sougata.shopping.presentation.homeRoot.util.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeScreenViewModel(
    private val repository: ShopRepository
) : ViewModel() {
    var state by mutableStateOf(HomeScreenState())

    private val _eventChannel = Channel<HomeScreenEvents>()
    val events = _eventChannel.receiveAsFlow()

    private var pageNo = 0
    private var pageCount = 0
    private val pageSize = 10

    init {
        viewModelScope.launch {
            repository.fetchAllProducts(pageNo, pageSize)
        }

        viewModelScope.launch {
            repository.saveAllCategories()
        }

        viewModelScope.launch {
            repository.fetchAllAddresses()
        }
        viewModelScope.launch {
            repository.fetchAllCarts()
        }

        viewModelScope.launch {
            repository.getAllOrders()
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.getAllProducts().map {
                it.map { product ->
                    val rating = getRandomNum(5)
                    val noOfRating = getRandomNum(1000)
                    val discountPercentage =
                        calculateDiscountPercentage(product.basePrice, product.modifiedPrice)
                    pageCount = product.pageCount
                    Product(
                        id = product.id.toString(),
                        productId = product.productId,
                        basePrice = product.basePrice.toString(),
                        category = product.category,
                        modifiedPrice = product.modifiedPrice.toString(),
                        unavaliable = product.unavaliable.toString(),
                        productName = product.productName,
                        stocks = product.stocks.toString(),
                        imageUrl = product.imageUrl,
                        rating = rating.toString(),
                        noOfRating = noOfRating.toString(),
                        discountPercentage = discountPercentage.toString()
                    )
                }
            }.collect {
                state = state.copy(products = it)
                if (state.products.isNotEmpty()){
                    state = state.copy(isLoading = false)
                }
            }
        }

        viewModelScope.launch {
            repository.getCart()
                .collect {
                    state = state.copy(cartCount = it.size)
                }
        }

    }

    fun onAction(action: HomeScreenActions) {
        when (action) {
            is HomeScreenActions.AddToCartClicked -> {

                viewModelScope.launch {
                    state = state.copy(
                        cartScreenState = CartScreenState(
                            cartItemId = action.product.productId,
                            isAddingToCart = true
                        )
                    )
                    val result =repository.addToCart(
                        ProductCart(
                            productId = action.product.productId,
                            productName = action.product.productName,
                            basePrice = action.product.basePrice.toDouble(),
                            stocks = action.product.stocks.toInt(),
                            imageUrl = action.product.imageUrl,
                            category = action.product.category,
                            modifiedPrice = action.product.modifiedPrice.toDouble(),
                            unavaliable = action.product.unavaliable.toBoolean()
                        )
                    )


                    state = state.copy(
                        cartScreenState = CartScreenState(
                            cartItemId = "",
                            isAddingToCart = false
                        )
                    )

                    when (result) {
                        is Result.Error -> {
                            _eventChannel.send(HomeScreenEvents.Failure(UiText.DynamicString("Failed to add to cart")))
                        }
                        is Result.Success -> {
                            _eventChannel.send(HomeScreenEvents.Success)
                        }
                    }
                }

            }
        }
    }

    fun loadMore() {
        if (pageNo >= --pageCount) return
        pageNo++
        viewModelScope.launch {
            repository.fetchAllProducts(pageNo, pageSize)
        }
    }

    private fun getRandomNum(maxNum: Int): Int {
        return Random.nextInt(1, maxNum)
    }

    private fun calculateDiscountPercentage(basePrice: Double, modifiedPrice: Double): Int {
        if (basePrice == 0.0) return 0
        return ((basePrice - modifiedPrice) / basePrice * 100).toInt()
    }
}