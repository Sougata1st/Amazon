package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sougata.core.presentation.designsystem.AmazonYellow

@Composable
fun AmazonAddressChip(modifier: Modifier = Modifier, text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(20.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(
                shape = RoundedCornerShape(5.dp),
                color = AmazonYellow.copy(alpha = 0.5f)
            )
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 8.dp)
    ) {
        Text(text = text, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
private fun Preview() {
    AmazonAddressChip(text = "HOME")
}