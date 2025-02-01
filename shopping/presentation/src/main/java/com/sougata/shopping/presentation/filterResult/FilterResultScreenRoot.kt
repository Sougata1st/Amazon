package com.sougata.shopping.presentation.filterResult

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.components.AmazonProductItem
import com.sougata.core.presentation.designsystem.components.AmazonScaffold
import com.sougata.core.presentation.designsystem.components.AmazonToolbar
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
                .background(color = AmazonGrey.copy(alpha = .2f))
        ) {
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

@Preview
@Composable
fun FilterResultScreenPreview() {
    AmazonTheme {
        FilterResultScreen(state = FilterResultScreenState(),
            onAction = {},

            loadMoreItems = {})
    }
}
