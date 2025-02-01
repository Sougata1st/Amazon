package com.sougata.core.presentation.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val LightColorScheme = lightColorScheme(
    primary = AmazonYellow,
    onPrimary = AmazonBlack,
    background = AmazonWhite,
    onBackground = AmazonDarkGrey,
    surfaceContainer = AmazonGrey,
    surface = AmazonWhite,
    onSurface = AmazonDarkGrey,
    error = AmazonRed
)

@Composable
fun AmazonTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}