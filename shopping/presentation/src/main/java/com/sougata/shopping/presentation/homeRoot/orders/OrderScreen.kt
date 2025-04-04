package com.sougata.shopping.presentation.homeRoot.orders

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonWhite
import com.sougata.core.presentation.designsystem.AmazonYellow
import com.sougata.shopping.presentation.R
import com.sougata.shopping.presentation.homeRoot.cart.util.formatDateTime
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrderListScreen(viewModel: OrderViewModel = koinViewModel()) {
    when {
        viewModel.state.isLoading -> {
            Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
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
                        text = "Your orders are loading..",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .graphicsLayer(alpha = alpha)
                    )
                }
            }
        }
        viewModel.state.orders.isEmpty() -> {
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

                Text(text = "No orders available", style = MaterialTheme.typography.bodyMedium, color = AmazonBlack,fontWeight = FontWeight.Bold)
            }
        }
        else -> {
            LazyColumn {
               items(viewModel.state.orders){
                   OrderItem(it)
               }
            }
        }
    }
}

@Composable
fun OrderItem(order: OrderItemUiState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = AmazonWhite),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Order ID: ")
                    }
                    append(order.orderId)
                },
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Status: ")
                    }
                    append(order.status)
                },
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Created At: ")
                    }
                    append(order.createdAt.formatDateTime())
                },
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Total Price: ")
                    }
                    append(order.totalPrice)
                },
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))
            AddressSection(order.address)
            Spacer(modifier = Modifier.height(8.dp))
            ProductListSection(order.productList)
        }
    }
}

@Composable
fun AddressSection(address: AddressUiState) {
    Column {
        Text(
            text = "Delivery Address",
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(text = "${address.locality}, ${address.landmark}, ${address.state}")
        Text(text = "Zip Code: ${address.zipCode}, Type: ${address.addressType}")
        Text(text = "Phone: ${address.phoneNumber}")
    }
}

@Composable
fun ProductListSection(products: List<ProductUiState>) {
    Column {
        Text(text = "Products:", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))

        LazyRow (
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(products){product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AmazonYellow, RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    AsyncImage(
                        model = product.productImageUrl,
                        contentDescription = "Product Image",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(text = product.productName, style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Price: ${product.productPrice}")
                        Text(text = "Quantity: ${product.quantity}")
                    }
                }
            }
        }

//        products.forEach { product ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//            ) {
//                AsyncImage(
//                    model = product.productImageUrl,
//                    contentDescription = "Product Image",
//                    modifier = Modifier
//                        .size(60.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Column {
//                    Text(text = product.productName, style = MaterialTheme.typography.bodyMedium)
//                    Text(text = "Price: ${product.productPrice}")
//                    Text(text = "Quantity: ${product.quantity}")
//                }
//            }
//        }
    }
}