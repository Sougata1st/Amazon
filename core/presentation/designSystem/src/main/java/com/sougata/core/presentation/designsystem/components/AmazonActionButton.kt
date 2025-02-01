package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonDarkGrey
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonYellow

@Composable
fun AmazonActionButton(
    text: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isEnabled: Boolean,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    progressbarSize: Dp = 20.dp
) {
    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(
            10.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) containerColor else AmazonGrey,
            contentColor = contentColor
        ),
        border = BorderStroke(
            width = 0.5.dp,
            color = AmazonDarkGrey,
        ),
        content = {
            Box(
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(progressbarSize),
                        color = AmazonBlack,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = text,
                        color = if (isEnabled) AmazonDarkGrey else AmazonDarkGrey.copy(alpha = 0.5f)
                    )
                }
            }

        }
    )
}


@Composable
@Preview(showSystemUi = true)
fun AmazonActionButtonPreview() {
    Row {
        AmazonActionButton(
            text = "Click",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            isLoading = false,
            isEnabled = true,
            containerColor = AmazonYellow,
            contentColor = AmazonDarkGrey,
            onClick = {

            }
        )
    }
}