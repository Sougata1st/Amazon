package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.sougata.core.presentation.designsystem.AmazonToolBarEndColor
import com.sougata.core.presentation.designsystem.AmazonToolBarStartColor

@Composable
fun AmazonGradient(
    content: @Composable () -> Unit
){
    Box(
        modifier = Modifier
            .fillMaxSize() // Full-screen Box
    ) {
        // Gradient at the top
        val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
        Box(
            modifier = Modifier
                .fillMaxWidth() // Horizontal fill
                .height(statusBarHeight) // Set the height of the gradient area
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            AmazonToolBarStartColor, // Start of the gradient
                            AmazonToolBarEndColor // End of the gradient
                        )
                    )
                )
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }

    }
}