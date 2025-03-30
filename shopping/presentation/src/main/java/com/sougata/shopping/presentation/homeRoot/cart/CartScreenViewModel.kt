package com.sougata.shopping.presentation.homeRoot.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.core.domain.util.Result
import com.sougata.core.presentation.ui.asUiText
import com.sougata.shopping.domain.models.ProductCart
import com.sougata.shopping.domain.repository.ShopRepository
import com.sougata.shopping.presentation.homeRoot.util.ProductCartItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class CartScreenViewModel(
    private val repository: ShopRepository
) : ViewModel() {
    var state by mutableStateOf(CartScreenState())

    private val _eventChannel = Channel<CartScreenEvents>()
    val events = _eventChannel.receiveAsFlow()


    init {
        viewModelScope.launch {
            val result = repository.fetchAllCarts()
            when (result) {
                is Result.Error -> {
                    _eventChannel.send(CartScreenEvents.FailedToFetchCarts)
                }

                is Result.Success -> {
                   _eventChannel.send(CartScreenEvents.Success)
                }
            }
        }
        viewModelScope.launch {
            repository.getCart()
                .map { cart ->
                    cart.sumOf {
                        it.quantity * it.product.modifiedPrice
                    }
                }
                .collect { totalSum ->
                    state = state.copy(
                        totalSUm = totalSum
                    )
                }
        }
        viewModelScope.launch {
            repository.getCart()
                .map {
                    it.map { productEntry ->
                        val rating = getRandomNum(5)
                        val noOfRating = getRandomNum(1000)
                        val discountPercentage = calculateDiscountPercentage(
                            productEntry.product.basePrice,
                            productEntry.product.modifiedPrice
                        )
                        val cartCount = productEntry.quantity
                        ProductCartItem(
                            id = Random.nextInt().toString(),
                            productId = productEntry.product.productId,
                            productName = productEntry.product.productName,
                            basePrice = productEntry.product.basePrice.toString(),
                            stocks = productEntry.product.stocks.toString(),
                            imageUrl = productEntry.product.imageUrl,
                            category = productEntry.product.category,
                            modifiedPrice = productEntry.product.modifiedPrice.toString(),
                            unavaliable = productEntry.product.unavaliable.toString(),
                            rating = rating.toString(),
                            noOfRating = noOfRating.toString(),
                            discountPercentage = discountPercentage.toString(),
                            cartCount = cartCount.toString()
                        )
                    }
                }.collect {
                    state = state.copy(products = it)
                }
        }

        repository.getAllAddress()
            .onEach {
                state = state.copy(
                    addressList = it
                )
            }.launchIn(viewModelScope)
    }

    fun onAction(event: CartActions) {
        when (event) {
            is CartActions.DecrementOneProduct -> {
                viewModelScope.launch {
                    repository.removeOneProductFromCart(event.productId)
                }
            }

            is CartActions.DeleteWholeProduct -> {
                viewModelScope.launch {
                    state = state.copy(
                        cartAddRemove = CartAddRemove(
                            cartItemId = event.productId,
                            isDeleting = true
                        )
                    )
                    repository.deleteWholeProductFromCart(event.productId)
                    state = state.copy(
                        cartAddRemove = CartAddRemove(
                            cartItemId = "",
                            isDeleting = false
                        )
                    )
                }
            }

            is CartActions.IncrementOneProduct -> {
                viewModelScope.launch {
                    repository.addToCart(
                        ProductCart(
                            productId = event.productCartItem.productId,
                            productName = event.productCartItem.productName,
                            basePrice = event.productCartItem.basePrice.toDouble(),
                            stocks = event.productCartItem.stocks.toInt(),
                            imageUrl = event.productCartItem.imageUrl,
                            category = event.productCartItem.category,
                            modifiedPrice = event.productCartItem.modifiedPrice.toDouble(),
                            unavaliable = event.productCartItem.unavaliable.toBoolean(),
                        )
                    )
                }
            }

            is CartActions.SelectAddress -> {
                state = state.copy(addressId = event.addressId)
            }

            is CartActions.ConfirmAndPurchaseClicked -> {
                viewModelScope.launch {
                    val response =
                        repository.initialisePayment(addressId = state.addressId)
                    when(response){
                        is Result.Error -> {
                            _eventChannel.send(CartScreenEvents.Failure(response.error.asUiText()))
                        }
                        is Result.Success -> {
                            state = state.copy(
                                amount = response.data.data.amount,
                                key = response.data.data.key,
                                orderId = response.data.data.orderId
                            )

                            _eventChannel.send(CartScreenEvents.InitialisePayment)
                        }
                    }
                }
            }
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

//consume ui events in home screen ie snackbar toast
// filter page
//this page ui events