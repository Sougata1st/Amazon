package com.sougata.shopping.presentation.addAdress

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.core.domain.util.Result
import com.sougata.core.presentation.ui.asUiText
import com.sougata.shopping.domain.models.Address
import com.sougata.shopping.domain.repository.ShopRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddAddressScreenViewModel(
    private val shopRepository: ShopRepository
) : ViewModel(){
    var state by mutableStateOf(AddAddressScreenState())

    private val _eventChannel = Channel<AddAddressScreenEvents>()
    val events = _eventChannel.receiveAsFlow()

    fun onAction(actions: AddAddressActions){
        when(actions){
            is AddAddressActions.AddAddressAddressType -> {
                state = state.copy(
                    addressType = actions.addressType
                )
            }
            AddAddressActions.AddAddressClicked -> {
                viewModelScope.launch {

                    state = state.copy(
                        isSavingAddress = true
                    )
                    val result = shopRepository.addAddress(
                        Address(
                            locality = state.locality,
                            landmark = state.landmark,
                            state = state.state,
                            zipCode = state.zipCode,
                            addressType = state.addressType,
                            phoneNumber = state.phoneNumber
                        )
                    )
                    state = state.copy(
                        isSavingAddress = false
                    )
                    when(result){
                        is Result.Error -> _eventChannel.send(AddAddressScreenEvents.Error(result.error.asUiText()))
                        is Result.Success -> _eventChannel.send(AddAddressScreenEvents.Success)
                    }
                }
            }
            is AddAddressActions.AddAddressLandmark -> {
                state = state.copy(
                    landmark = actions.landmark
                )
            }
            is AddAddressActions.AddAddressLocality -> {
                state = state.copy(
                    locality = actions.locality
                )
            }
            is AddAddressActions.AddAddressPhoneNumber -> {
                state = state.copy(
                    phoneNumber = actions.phoneNumber
                )
            }
            is AddAddressActions.AddAddressState -> {
                state = state.copy(
                    state = actions.state
                )
            }
            is AddAddressActions.AddAddressZipCode -> {
                state = state.copy(
                    zipCode = actions.zipCode
                )
            }
        }
    }
}