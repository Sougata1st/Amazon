package com.sougata.shopping.presentation.homeRoot.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.AmazonYellow
import com.sougata.core.presentation.designsystem.components.AmazonActionButton
import com.sougata.core.presentation.designsystem.components.AmazonCartItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartCentreContentScreenRoot(
    viewModel: CartScreenViewModel = koinViewModel()
) {
    CartCentreContentScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun CartCentreContentScreen(
    state: CartScreenState,
    onAction: (CartActions) -> Unit
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row (
            modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text(text = "Total: ${state.totalSUm}")
            Spacer(Modifier.width(10.dp))
            AmazonActionButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Purchase",
                isLoading = false,
                isEnabled = true,
                containerColor = AmazonYellow,
                onClick = {
                    onAction(CartActions.Purchase)
                },
                contentColor = AmazonBlack
            )
        }

        Spacer(Modifier.height(10.dp))


        LazyColumn {
            items(state.products.size) { index ->

                val product = state.products[index]

                AmazonCartItem(
                    imageUrl = product.imageUrl,
                    brandName = product.productName,
                    category = product.category,
                    rating = product.rating,
                    noOfRating = product.noOfRating,
                    mrp = product.basePrice,
                    discountPercentage = product.discountPercentage,
                    actualPrice = product.basePrice,
                    isDeleting = state.cartAddRemove.cartItemId == product.productId && state.cartAddRemove.isDeleting,
                    cartCount = product.cartCount,
                    onDeleteClick = {
                        onAction(CartActions.DeleteWholeProduct(product.productId))
                    },
                    onIncrementClicked = {
                        onAction(CartActions.IncrementOneProduct(product))
                    },
                    onDecrementClick = {
                        onAction(CartActions.DecrementOneProduct(product.productId))
                    },
                )
                Spacer(Modifier.height(10.dp))

            }
        }
    }


}

@Preview
@Composable
fun CartCentreContentScreenPreview() {
    AmazonTheme {
        CartCentreContentScreen(
            state = CartScreenState(),
            onAction = {})
    }
}
