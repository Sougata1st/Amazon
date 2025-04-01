package com.sougata.shopping.presentation.homeRoot.home

import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sougata.core.presentation.designsystem.components.AmazonProductItem
import com.sougata.core.presentation.ui.ObserveAsEvents
import com.sougata.shopping.presentation.R
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
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
                val progress by animateLottieCompositionAsState(
                    composition,
                    iterations = LottieConstants.IterateForever
                )

                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                    modifier = Modifier.size(200.dp)
                )
                Spacer(Modifier.height(10.dp))

                val alpha by rememberInfiniteTransition().animateFloat(
                    initialValue = 0.3f,
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1000, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                Text(
                    text = "Your items are loading..",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .graphicsLayer(alpha = alpha)
                )
            }
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