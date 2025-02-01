package com.sougata.core.presentation.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sougata.core.presentation.designsystem.AmazonMediumGrey
import com.sougata.core.presentation.designsystem.EyeClosedIcon
import com.sougata.core.presentation.designsystem.EyeOpenIcon

@Composable
fun AmazonSecureTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    textFontColor: Color,
    hintFontColor: Color,
    startIconTint: Color,
    endIconTint: Color,
    focusColor: Color,
    unfocusedColor: Color,
    borderColor: Color,
    onValueChange: (text: String)->Unit,
    isEnabled: Boolean,
    keyboardType: KeyboardType,
    cursorBrushColor: Color,
    startIcon: ImageVector
){
    var isFocused by rememberSaveable{
        mutableStateOf(false)
    }

    var isPassVisible by rememberSaveable { mutableStateOf(false) }

    BasicTextField(
        value = text,
        textStyle = TextStyle(color = textFontColor),
        onValueChange = {enteredText->
            onValueChange(enteredText)
        },
        enabled = isEnabled,
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            keyboardType = keyboardType
        ),
        cursorBrush = SolidColor(cursorBrushColor),
        visualTransformation = if (isPassVisible) VisualTransformation.None else PasswordVisualTransformation(),
        decorationBox = {input->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Start Icon
                Icon(
                    imageVector = startIcon,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = startIconTint
                )
                Spacer(modifier = Modifier.width(10.dp))

                // Text Input
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart// Expand to fill available space
                ) {
                    if (text.isNotEmpty()){
                        input()
                    }else{
                        input()
                        if (!isFocused){
                            Text(text = hint, color = hintFontColor)
                        }
                    }
                }

                IconButton(
                    modifier = Modifier.size(30.dp),
                    onClick = {
                        isPassVisible = !isPassVisible
                    }
                ) {
                    if (isPassVisible) {
                        Icon(
                            imageVector = EyeClosedIcon,
                            contentDescription = null,
                            tint = endIconTint
                        )
                    } else {
                        Icon(
                            imageVector = EyeOpenIcon,
                            contentDescription = null,
                            tint = endIconTint
                        )
                    }
                }
            }

        },
        modifier = modifier.
        fillMaxWidth()
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .background(
                color = if (isFocused) focusColor else unfocusedColor,
                shape = RoundedCornerShape(10.dp)
            )
            .border(
                color = if(isFocused) borderColor else Color.Transparent,
                width = 1.dp,
                shape = RoundedCornerShape(
                    10.dp
                )
            )
            .padding(10.dp)
    )
}

@Composable
@Preview
fun AmazonSecureTextFieldPreview(){
    AmazonSecureTextField(
        text = "sougata",
        hint = "Enter your pass",
        textFontColor = Color.Black,
        startIconTint = AmazonMediumGrey,
        endIconTint = AmazonMediumGrey,
        hintFontColor = AmazonMediumGrey.copy(
            alpha = 0.5f
        ),
        onValueChange = {

        },
        isEnabled = true,
        keyboardType = KeyboardType.Text,
        cursorBrushColor = Color.Black,
        startIcon = EyeClosedIcon,
        focusColor = Color.Cyan,
        unfocusedColor = Color.White,
        borderColor = Color.Black
    )
}