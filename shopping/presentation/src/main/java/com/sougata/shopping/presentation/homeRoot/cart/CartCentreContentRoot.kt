package com.sougata.shopping.presentation.homeRoot.cart

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.razorpay.Checkout
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.AmazonYellow
import com.sougata.core.presentation.designsystem.components.AmazonActionButton
import com.sougata.core.presentation.designsystem.components.AmazonCartItem
import com.sougata.core.presentation.designsystem.components.AmazonSelectAddressItem
import com.sougata.core.presentation.ui.ObserveAsEvents
import org.json.JSONObject
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartCentreContentScreenRoot(
    viewModel: CartScreenViewModel = koinViewModel()
) {
    val context = LocalContext.current

    val activity = context as Activity

    ObserveAsEvents(viewModel.events) {
        when(it){

            is CartScreenEvents.Failure -> {
                Toast.makeText(context," Failed to make payment", Toast.LENGTH_SHORT).show()
            }
            CartScreenEvents.Success -> {
                Toast.makeText(context," Fetched all carts", Toast.LENGTH_SHORT).show()
            }

            CartScreenEvents.FailedToFetchCarts -> {
                Toast.makeText(context," Failed to fetch cart", Toast.LENGTH_SHORT).show()
            }

            CartScreenEvents.InitialisePayment -> {
                initiatePayment(activity,viewModel.state.amount, viewModel.state.orderId)
            }
        }
    }

    CartCentreContentScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CartCentreContentScreen(
    state: CartScreenState,
    onAction: (CartActions) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Total: ${state.totalSUm}")
            Spacer(Modifier.width(10.dp))
            AmazonActionButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Purchase",
                isLoading = false,
                isEnabled = true,
                containerColor = AmazonYellow,
                onClick = {
                    showSheet = true
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

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Text("Select Delivery Address", modifier = Modifier.padding(16.dp))

            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.addressList){
                    AmazonSelectAddressItem(
                        id = it.id.toString(),
                        selectedId = state.addressId.toString(),
                        locality = it.locality,
                        landmark = it.landmark,
                        state = it.state,
                        zipCode = it.zipCode,
                        phoneNumber = it.phoneNumber,
                        addressType = it.addressType,
                        onClick = {
                            onAction(CartActions.SelectAddress(it))
                        }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    onAction(CartActions.ConfirmAndPurchaseClicked)
                    showSheet = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                enabled = state.addressId != -1
            ) {
                Text("Confirm and Purchase")
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

fun initiatePayment(activity: Activity,amount: Int, orderId: String) {
    val checkout = Checkout()
    checkout.setKeyID("rzp_test_KKsSpLmyNmWyvn") // Replace with your actual key

    try {
        val options = JSONObject()
        options.put("name", "Amazon")
        options.put("description", "Payment for your Order")
        options.put("currency", "INR")
        options.put("amount", amount/100) // Amount in paise (e.g., 50000 paise = ₹500)
        options.put("order_id", orderId)

        val prefill = JSONObject()
        prefill.put("email", "skar7340@gmail.com")
        prefill.put("contact", "8710099831")
        options.put("prefill", prefill)

        checkout.open(activity, options)
    } catch (e: Exception) {
        Toast.makeText(activity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
    }
}
