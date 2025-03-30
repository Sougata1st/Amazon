package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonDarkGrey
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonYellow

@Composable
fun AmazonAddressCard(
    locality: String,
    landmark: String,
    state: String,
    zipCode: String,
    phoneNumber: String,
    addressType: String,
    isDeleting: Boolean ,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = AmazonYellow,
            contentColor = AmazonBlack,
            disabledContainerColor = AmazonGrey,
            disabledContentColor = AmazonDarkGrey,
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isDeleting) {
                            Modifier.blur(1.5.dp)
                        } else {
                            Modifier
                        }
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = locality,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = AmazonBlack
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(text = landmark, fontSize = 18.sp)
                    Text(text = state, fontSize = 18.sp)
                    Text(text = zipCode, fontSize = 18.sp)
                    Text(text = phoneNumber, fontSize = 18.sp)
                    Text(text = addressType, fontSize = 18.sp)
                }

                IconButton(onClick = { onDelete() }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Address")
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


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun AmazonAddressCardPrev() {
    AmazonAddressCard(
        locality = "Gopiballavpur",
        landmark = "Naer Khanti Pri School",
        state = "West Bengal",
        zipCode = "721506",
        phoneNumber = "8710099831",
        addressType = "HOME",
        onDelete = {},
        isDeleting = false
    )
}