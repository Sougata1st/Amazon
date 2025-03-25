package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonWhite
import com.sougata.core.presentation.designsystem.AmazonYellow

@Composable
fun AmazonDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    message: String,
    confirmButtonText: String,
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AmazonWhite),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AmazonBlack
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = message,
                    fontSize = 16.sp,
                    color = AmazonBlack,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AmazonActionButton(
                        text = confirmButtonText,
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        isLoading = false,
                        isEnabled = true,
                        containerColor = AmazonYellow,
                        contentColor = AmazonBlack,
                        onClick = onConfirm,
                        progressbarSize = 20.dp
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
private fun Preview() {
    AmazonDialog(onConfirm = {}, onDismiss = {}, title = "Requesting Permission", message = "In order to get your location you need to give permission", confirmButtonText = "Sougata")
}