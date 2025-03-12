package com.sougata.shopping.presentation.addAdress

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class AddAddressScreenViewModel : ViewModel(){
    var state by mutableStateOf(AddAddressScreenState())

    private val _eventChannel = Channel<AddAddressScreenEvents>()
    val events = _eventChannel.receiveAsFlow()

    fun onAction(actions: AddAddressActions){

    }
}