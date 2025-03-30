package com.sougata.shopping.presentation.showAllAddress

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.shopping.domain.repository.ShopRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ShowAddressViewModel(
    private val shopRepository: ShopRepository
) : ViewModel() {
    var state by mutableStateOf(ShowAddressState())

    private val _eventChannel = Channel<ShowAddressEvents>()
    val events = _eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            shopRepository.getAllAddress()
                .onEach {
                    state = state.copy(
                        addressList = it
                    )
                }.launchIn(this)
        }
    }
    fun onAction(actions: ShowAddressActions) {
        when (actions) {
            is ShowAddressActions.DeleteClicked -> {

                viewModelScope.launch {
                    state = state.copy(
                        addressAddRemove = AddressAddRemove(
                            addressId = actions.addressId,
                            isDeleting = true
                        )
                    )
                    shopRepository.deleteAddress(actions.addressId.toString())
                    state = state.copy(
                        addressAddRemove = AddressAddRemove(
                            addressId = -1,
                            isDeleting = false
                        )
                    )
                }

            }
        }
    }

}