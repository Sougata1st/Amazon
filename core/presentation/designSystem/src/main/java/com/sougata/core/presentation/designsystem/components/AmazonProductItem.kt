package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonRed
import com.sougata.core.presentation.designsystem.AmazonWhite
import com.sougata.core.presentation.designsystem.AmazonYellow

@Composable
fun AmazonProductItem(
    isAddingToCart:Boolean,
    imageUrl: String,
    brandName: String,
    category: String,
    rating: String,
    noOfRating: String,
    mrp: String,
    discountPercentage: String,
    actualPrice: String,
    onAddToCartClicked: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp), colors = CardColors(
            containerColor = AmazonWhite,
            contentColor = AmazonBlack,
            disabledContainerColor = AmazonGrey,
            disabledContentColor = AmazonGrey
        ),
        modifier = Modifier.padding(start = 10.dp, end = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .padding(10.dp)
            )


            Column {
                Text(
                    text = brandName, maxLines = 2, style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        letterSpacing = 0.5.sp,
                        lineHeight = 30.sp
                    )
                )
                Spacer(Modifier.height(5.dp))
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier
                        .background(
                            color = AmazonYellow.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(3.dp)
                        )
                        .padding(3.dp)
                ) {
                    Text(text = category, fontSize = 10.sp)
                }
                Spacer(Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StarRatingBar(
                        rating = rating.toFloat(), modifier = Modifier
                            .width(60.dp)
                            .height(10.dp)
                    )
                    Row(modifier = Modifier.weight(.45f)) {
                        Text(text = rating, fontSize = 13.sp)
                        Spacer(Modifier.width(5.dp))
                        Text("($noOfRating)", fontSize = 13.sp)
                    }

                }
                Spacer(Modifier.height(5.dp))
                PriceChip(
                    mrp = mrp, discountPercentage = discountPercentage, actualPrice = actualPrice
                )

                Spacer(modifier = Modifier.height(15.dp))
                AmazonActionButton(
                    text = "Add to cart",
                    isLoading = isAddingToCart,
                    isEnabled = true,
                    containerColor = AmazonYellow,
                    onClick = {
                        onAddToCartClicked()
                    },
                    modifier = Modifier.height(35.dp),
                    contentColor = AmazonBlack
                )
            }
        }
    }
}


@Composable
fun StarRatingBar(
    modifier: Modifier = Modifier,
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit = {}
) {
    val density = LocalDensity.current.density
    val starSize = (12f * density).dp
    val starSpacing = (0.5f * density).dp

    Row(
        modifier = modifier.selectableGroup(), verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val starRating = i.toFloat()
            val icon = when {
                starRating <= rating -> Icons.Filled.Star
                starRating - 0.5f <= rating -> Icons.AutoMirrored.Filled.StarHalf // Use a half-filled star icon
                else -> Icons.Default.Star
            }
            val iconTintColor = when {
                starRating <= rating -> Color(0xFFFFC700)
                starRating - 0.5f <= rating -> Color(0xFFFFC700) // Same tint for half-star
                else -> Color(0x20FFFFFF)
            }

            Icon(imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .size(10.dp)
                    .selectable(selected = starRating <= rating, onClick = {
                        onRatingChanged(starRating - 0.5f)
                    })
                    .width(starSize)
                    .height(starSize))

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}


@Composable
fun PriceChip(
    mrp: String, discountPercentage: String, actualPrice: String
) {
    Row(
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = "₹$actualPrice", style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                letterSpacing = 0.5.sp,
                lineHeight = 30.sp
            )
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text("MRP", fontSize = 18.sp)
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = mrp, style = TextStyle(
                textDecoration = TextDecoration.LineThrough, fontSize = 18.sp, color = Color.Black
            ),
            color = AmazonRed
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text("$discountPercentage% off")
    }
}

