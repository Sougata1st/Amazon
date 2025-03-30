package com.sougata.shopping.presentation.homeRoot.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sougata.core.presentation.designsystem.components.AmazonProductItem
import com.sougata.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeCentreContentRoot(
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    HomeCentreContent(
        loadMoreItems = {
            viewModel.loadMore()
        },
        onAction = {
            viewModel.onAction(it)
        },
        state = viewModel.state
    )
    val context = LocalContext.current
    ObserveAsEvents(viewModel.events) {
        when(it){
            is HomeScreenEvents.Failure -> {
                Toast.makeText(context,"Failed to add to cart", Toast.LENGTH_LONG).show()
            }
            HomeScreenEvents.Success -> {
                Toast.makeText(context,"Added to cart", Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun HomeCentreContent(
    loadMoreItems: () -> Unit,
    onAction: (HomeScreenActions) -> Unit,
    state: HomeScreenState
) {
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(state.products.size) { index ->

                val product = state.products[index]

                AmazonProductItem(
                    imageUrl = product.imageUrl,
                    brandName = product.productName,
                    category = product.category,
                    rating = product.rating,
                    noOfRating = product.noOfRating,
                    mrp = product.basePrice,
                    discountPercentage = product.discountPercentage,
                    actualPrice = product.basePrice,
                    isAddingToCart = state.cartScreenState.cartItemId == product.productId && state.cartScreenState.isAddingToCart,
                    onAddToCartClicked = {
                        onAction(HomeScreenActions.AddToCartClicked(product))
                    }
                )
                Spacer(Modifier.height(10.dp))

                if (index == state.products.size - 1) {
                    loadMoreItems()
                }
            }
        }
    }
}