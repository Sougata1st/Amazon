package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonYellow

@Composable
fun AmazonAddressTabItem(
    modifier: Modifier = Modifier,
    selectedTab: Int,
    text: String,
    imageVector: ImageVector,
    position: Int = 0,
    onClick: (Int) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                shape = RoundedCornerShape(8.dp),
                color = AmazonYellow.copy(alpha = 0.5f)
            )
            .then(
                if (selectedTab == position) {
                    Modifier.border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(8.dp)
                    )
                } else {
                    Modifier
                }
            )
            .clickable {
                onClick(position)
            }
    )
    {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Spacer(Modifier.width(4.dp))
            Icon(imageVector = imageVector, contentDescription = null, tint = if (selectedTab == position) Color.Black else AmazonGrey)
            Text(
                text = text,
                color = if (selectedTab == position) Color.Black else AmazonGrey,
                style = TextStyle(fontWeight = if (selectedTab == position) FontWeight.Bold else FontWeight.Normal)
            )
            Spacer(Modifier.width(4.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AmazonAddressTabItemPrev() {
    AmazonAddressTabItem(
        selectedTab = 0, text = "Sougata", imageVector = Icons.Default.Home, position = 0, onClick = {

        }
    )
}