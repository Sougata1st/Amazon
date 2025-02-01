package com.sougata.shopping.presentation.filter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.shopping.domain.repository.ShopRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class FilterViewModel(
    private val repository: ShopRepository
):ViewModel() {
    var state by mutableStateOf(FilterScreenState())

    private val _channel = Channel<FilterScreenEvents>()
    val channel = _channel.receiveAsFlow()
    init {
        viewModelScope.launch {
            repository.getAllCategories().map {
                it.map {
                    it.name
                }
            }.collect{
                state = state.copy(
                    selectedCategory = it[0]
                )
                state = state.copy(categoryList = it)
            }
        }
    }


    fun onAction(action:FilterScreenActions){
        when(action){
            FilterScreenActions.ApplyClicked -> {
                viewModelScope.launch {
                    repository.clearFilterItems()
                }
            }
            is FilterScreenActions.CategorySelectionTabClicked -> {
                state = state.copy(
                    selectedCategory = action.category
                )
            }
            is FilterScreenActions.PriceBoundSelectionTabClicked -> {
                state = state.copy(
                    priceRange = action.priceRange
                )
            }
            is FilterScreenActions.SortBySelectionTabClicked -> {
                state = state.copy(
                    selectedSortBy = action.sortBy
                )
            }
            is FilterScreenActions.SortDirectionSelectionTabClicked -> {
                state = state.copy(
                    selectedSortDirection = action.sortDir
                )
            }
        }
    }
}