package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonWhite
import com.sougata.core.presentation.designsystem.AmazonYellow

@Composable
fun AmazonCartItem(
    isDeleting:Boolean,
    imageUrl: String,
    brandName: String,
    category: String,
    rating: String,
    noOfRating: String,
    mrp: String,
    discountPercentage: String,
    actualPrice: String,
    cartCount: String,
    onDeleteClick: () -> Unit,
    onIncrementClicked: () -> Unit,
    onDecrementClick: () -> Unit
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
        Box(modifier = Modifier.fillMaxWidth()){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp).then(
                        if (isDeleting) {
                            Modifier.blur(1.5.dp)
                        } else {
                            Modifier
                        }
                        ), verticalAlignment = Alignment.CenterVertically
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

                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        AmazonCartButton(
                            cartCount = cartCount,
                            onIncrementClicked = onIncrementClicked,
                            onDecrementClick = onDecrementClick
                        )

                        Spacer(Modifier.width(30.dp))

                        Image(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                onDeleteClick()
                            }
                        )

                    }

                }
            }
            if (isDeleting){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                        .size(50.dp),
                    color = AmazonBlack
                )
            }
        }
    }
}


@Preview
@Composable
private fun AmazonCartItemPreview() {
    AmazonCartItem(
        isDeleting = false,
        imageUrl = "",
        brandName = "Adidas",
        category = "Clothes",
        rating = "5",
        noOfRating = "234",
        mrp = "1200",
        discountPercentage = "88",
        actualPrice = "343",
        cartCount = "8",
        onDeleteClick = {},
        onIncrementClicked = {},
        onDecrementClick = {}
    )
}

@Composable
fun AmazonCartButton(
    cartCount:String,
    onIncrementClicked: () -> Unit,
    onDecrementClick: () -> Unit
) {
    Box(
        modifier = Modifier.background(
            shape = RoundedCornerShape(20.dp), color = AmazonYellow.copy(
                alpha = .5f
            )
        )
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.clickable { onIncrementClicked() }
            )
            Spacer(Modifier.width(15.dp))
            Text(text = cartCount, style = TextStyle(fontWeight = FontWeight.Bold))
            Spacer(Modifier.width(15.dp))
            Icon(
                imageVector = Icons.Default.Remove,
                contentDescription = null,
                modifier = Modifier.clickable { onDecrementClick() }
            )
        }
    }
}

@Preview
@Composable
private fun AmazonCartButtonPrev() {
    AmazonCartButton(
        cartCount = 5.toString(),
        onIncrementClicked = {},
        onDecrementClick = {}
    )
}