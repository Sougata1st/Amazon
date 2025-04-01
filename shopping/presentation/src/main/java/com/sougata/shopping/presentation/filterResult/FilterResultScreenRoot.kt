package com.sougata.shopping.presentation.filterResult

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.components.AmazonProductItem
import com.sougata.core.presentation.designsystem.components.AmazonScaffold
import com.sougata.core.presentation.designsystem.components.AmazonToolbar
import com.sougata.shopping.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun FilterResultScreenRoot(viewModel: FilterResultScreenViewModel = koinViewModel()) {
    FilterResultScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        loadMoreItems = {
            viewModel.loadMore()
        }
    )
}

@Composable
private fun FilterResultScreen(
    state: FilterResultScreenState,
    onAction: (FilterResultScreenActions) -> Unit,
    loadMoreItems: () -> Unit
) {
    AmazonScaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            AmazonToolbar(showBackBtn = false,
                gradientColors = listOf(
                    AmazonGrey.copy(alpha = 0.2f),
                    AmazonGrey.copy(alpha = 0.2f)
                ),
                showSearchBar = false,
                showCustomTitle = true,
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Text(text = "Limited Time Deals", fontSize = 20.sp)
                    }


                })
        }
    ){
        Box(
            modifier = Modifier
                .padding(paddingValues = it)
                .fillMaxSize()
                .background(color = AmazonGrey.copy(alpha = .2f)),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading) {
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
                        text = "Your Items are loading...",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .graphicsLayer(alpha = alpha)
                    )
                }
            }else {
                if (state.products.isEmpty()) {
                    Column (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty))
                        val progress by animateLottieCompositionAsState(
                            composition,
                            iterations = LottieConstants.IterateForever
                        )

                        LottieAnimation(
                            composition = composition,
                            progress = { progress },
                            modifier = Modifier.size(300.dp)
                        )
                        Spacer(Modifier.height(10.dp))

                        Text(
                            text = "No products found..",
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else{
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
                                    onAction(FilterResultScreenActions.AddToCartClicked(product))
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
        }
    }
}

@Preview
@Composable
fun FilterResultScreenPreview() {
    AmazonTheme {
        FilterResultScreen(state = FilterResultScreenState(),
            onAction = {},

            loadMoreItems = {})
    }
}
