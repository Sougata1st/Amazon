package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sougata.core.presentation.designsystem.AmazonDarkGrey
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonMediumGrey
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.AmazonToolBarEndColor
import com.sougata.core.presentation.designsystem.AmazonToolBarStartColor
import com.sougata.core.presentation.designsystem.AmazonWhite
import com.sougata.core.presentation.designsystem.BackArrowIcon
import com.sougata.core.presentation.designsystem.GoogleLensIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmazonToolbar(
    text: String = "",
    showBackBtn: Boolean,
    showSearchBar: Boolean,
    gradientColors: List<Color>,
    onBackClick: () -> Unit = {},
    title: @Composable () -> Unit = {},
    showCustomTitle: Boolean = false,
    navigationIconColor:Color = AmazonGrey,
    onValueChange: (newText: String) -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.background(
            brush = Brush.horizontalGradient(
                colors = gradientColors
            )
        ),
        navigationIcon = {
            if (showBackBtn) {
                IconButton(
                    modifier = Modifier.size(40.dp),
                    onClick = {
                        onBackClick()
                    }
                ) {
                    Row {
                        Spacer(Modifier.width(10.dp))
                        Icon(
                            imageVector = BackArrowIcon,
                            contentDescription = null,
                            tint = navigationIconColor,
                            modifier = Modifier.size(25.dp)
                        )
                    }

                }
            }
        },
        title = {
            if (showCustomTitle){
                title()
            }
            else if (showSearchBar){
                AmazonSearchBar(text = text, onValueChange = {
                    onValueChange(it)
                })
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
@Preview(showSystemUi = true)
fun AmazonToolbarPreview() {
    AmazonToolbar(
        showBackBtn = true, text = "",
        onBackClick = {

        },
        onValueChange = {

        },
        gradientColors = listOf(AmazonToolBarStartColor, AmazonToolBarEndColor),
        showSearchBar = true
    )
}

@Composable
fun AmazonSearchBar(
    text: String,
    onValueChange: (newText: String) -> Unit
) {
    AmazonTextField(
        modifier = Modifier.padding(end = 16.dp),
        text = text,
        hint = "Search Amazon.in",
        textFontColor = AmazonDarkGrey,
        hintFontColor = AmazonMediumGrey.copy(
            alpha = 0.5f
        ),
        startIconTint = AmazonMediumGrey,
        endIconTint = AmazonMediumGrey,
        focusColor = AmazonWhite,
        unfocusedColor = AmazonWhite,
        borderColor = AmazonDarkGrey,
        onValueChange = {
            onValueChange(it)
        },
        isEnabled = true,
        keyboardType = KeyboardType.Text,
        cursorBrushColor = AmazonDarkGrey,
        startIcon = Icons.Default.Search,
        endIcon = GoogleLensIcon
    )
}

@Composable
@Preview
fun AmazonSearchBarPreview() {
    AmazonTheme {
        AmazonSearchBar(text = "") { }
    }
}