package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Colors
val AmazonLightYellow = Color(0xFFFCF2B7)
val AmazonBlack = Color(0xFF232F3E)
val AmazonGrey = Color(0xFFF0F0F0)
val AmazonPink = Color(0XFFE3B1B0)

@Composable
fun AmazonSelectAddressItem(
    id: String,
    selectedId: String,
    locality: String,
    landmark: String,
    state: String,
    zipCode: String,
    phoneNumber: String,
    addressType: String,
    onClick: (Int) -> Unit
) {
    val isSelected = id == selectedId
    Card(
        modifier = Modifier
            .padding(12.dp)
            .wrapContentWidth()
            .clickable {
                onClick(id.toInt())
            }
            .border(
                width = if (isSelected) 4.dp else 1.dp,
                color = if (isSelected) AmazonPink else Color.Black,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) AmazonLightYellow.copy(alpha = 0.9f) else AmazonLightYellow,
            contentColor = AmazonBlack
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.wrapContentWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.wrapContentWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    AddressText(label = "Locality: ", value = locality)
                    AddressText(label = "Landmark: ", value = landmark)
                    AddressText(label = "State: ", value = state)
                    AddressText(label = "Zip Code: ", value = zipCode)
                    AddressText(label = "Phone: ", value = phoneNumber)
                    AddressText(label = "Address Type: ", value = addressType)
                }

                // Round box with a dot inside if selected
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(AmazonPink.copy(alpha = 0.2f), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(AmazonPink, shape = CircleShape)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AddressText(label: String, value: String) {
    Row {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = AmazonBlack
        )
        Text(
            text = value,
            fontSize = 18.sp,
            color = AmazonBlack
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AmazonSelectAddressItemPreview() {
    Column {

    }
}
