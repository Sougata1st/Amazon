package com.sougata.shopping.presentation.filterResult

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.core.domain.util.Result
import com.sougata.core.presentation.ui.UiText
import com.sougata.shopping.domain.models.ProductCart
import com.sougata.shopping.domain.repository.ShopRepository
import com.sougata.shopping.presentation.homeRoot.home.CartScreenState
import com.sougata.shopping.presentation.homeRoot.util.Product
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class FilterResultScreenViewModel(
    private val repository: ShopRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var state by mutableStateOf(FilterResultScreenState())

    private var pageNo = 0
    private var pageCount = 0
    private val pageSize = 10
    private var category:String=""
    private var sortBy:String =""
    private var sortDirection:String =""
    private var lowerPriceBound:Int = 0
    private var upperPriceBound:Int = Int.MAX_VALUE

    private val _eventChannel = Channel<FilterResultScreenEvents>()
    val eventChannel = _eventChannel.receiveAsFlow()

    init {
        category = savedStateHandle["category"] ?: "Clothes"
        sortBy = savedStateHandle["sortBy"] ?: "category"
        sortDirection = savedStateHandle["sortDirection"] ?: "DESC"

        lowerPriceBound = savedStateHandle["lowerPriceBound"] ?: 0
        upperPriceBound = savedStateHandle["upperPriceBound"] ?: Int.MAX_VALUE

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.fetchAllFilteredProducts(
                pageNo,
                pageSize,
                category,
                sortBy,
                sortDirection,
                lowerPriceBound,
                upperPriceBound
            )
            state = state.copy(isLoading = false)
        }

        viewModelScope.launch {
            repository.getAllFilteredProducts(sortDirection).collect {

                state = state.copy(
                    products = it.map {
                        val rating = getRandomNum(5)
                        val noOfRating = getRandomNum(1000)
                        val discountPercentage =
                            calculateDiscountPercentage(it.basePrice, it.modifiedPrice)
                        pageCount = it.pageCount
                        Product(
                            id = it.id.toString(),
                            productId = it.productId,
                            productName = it.productName,
                            basePrice = it.basePrice.toString(),
                            stocks = it.stocks.toString(),
                            imageUrl = it.imageUrl,
                            category = it.category,
                            modifiedPrice = it.modifiedPrice.toString(),
                            unavaliable = it.unavaliable.toString(),
                            rating = rating.toString(),
                            noOfRating = noOfRating.toString(),
                            discountPercentage = discountPercentage.toString()
                        )
                    }
                )
            }
        }
    }

    fun onAction(action: FilterResultScreenActions) {
        when(action){
            is FilterResultScreenActions.AddToCartClicked -> {
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
                            _eventChannel.send(FilterResultScreenEvents.Failure(UiText.DynamicString("Failed to add to cart")))
                        }
                        is Result.Success -> {
                            _eventChannel.send(FilterResultScreenEvents.Success)
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
            repository.fetchAllFilteredProducts(pageNo, pageSize,category,sortBy,sortDirection,lowerPriceBound,upperPriceBound)
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