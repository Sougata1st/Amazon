package com.sougata.shopping.presentation.homeRoot.orders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.core.domain.util.Result
import com.sougata.shopping.domain.repository.ShopRepository
import kotlinx.coroutines.launch

class OrderViewModel(
    private val repository: ShopRepository
):ViewModel() {

    var state by mutableStateOf(OrderUiState())

    fun load(){
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result =
                repository.getAllOrders()
            when(result){
                is Result.Error -> {
                    state = state.copy(
                        isLoading = false
                    )
                }
                is Result.Success -> {
                    state = state.copy(
                        isLoading = false,
                        orders = result.data.data.content.map { it.toOrderItemUiState() }
                    )
                }
            }
        }
    }

}