package com.sougata.shopping.presentation.addAdress

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.AddHome
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonDarkGrey
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonMediumGrey
import com.sougata.core.presentation.designsystem.AmazonTextFieldFocusedColor
import com.sougata.core.presentation.designsystem.AmazonTextFieldUnfocusedColor
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.components.AmazonActionButton
import com.sougata.core.presentation.designsystem.components.AmazonScaffold
import com.sougata.core.presentation.designsystem.components.AmazonTextField
import com.sougata.core.presentation.designsystem.components.AmazonToolbar
import com.sougata.shopping.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddAddressScreenRoot(
    onBackClick: () -> Unit,
    viewModel:AddAddressScreenViewModel  = koinViewModel()
) {
    AddAddressScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        onBackClick = onBackClick
    )
}

@Composable
private fun AddAddressScreen(
    onBackClick: () -> Unit,
    state: AddAddressScreenState,
    onAction: (AddAddressActions) -> Unit
) {
    AmazonScaffold(
        topBar = {
            AmazonToolbar(
                showCustomTitle = true,
                title = {
                    Box (
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Add delivery Address",
                            style = TextStyle(
                                fontSize = 20.sp, // Adjust size as needed
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif, // You can change the font family
                                color = Color.Black // Adjust color if needed
                            )
                        )
                    }
                },
                showBackBtn = true,
                gradientColors = listOf(
                    AmazonGrey.copy(alpha = 0.2f),
                    AmazonGrey.copy(alpha = 0.2f)
                ),
                showSearchBar = false,
                onBackClick = {
                    onBackClick()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                AmazonTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .width(50.dp),
                    text = state.zipCode,
                    hint = stringResource(R.string.pincode_required),
                    textFontColor = AmazonBlack,
                    hintFontColor = AmazonGrey,
                    startIconTint = AmazonMediumGrey,
                    endIconTint = AmazonMediumGrey,
                    focusColor = AmazonTextFieldFocusedColor,
                    unfocusedColor = AmazonTextFieldUnfocusedColor,
                    borderColor = AmazonDarkGrey,
                    onValueChange = {
                        onAction(AddAddressActions.AddAddressZipCode(it))
                    },
                    isEnabled = true,
                    keyboardType = KeyboardType.Email,
                    cursorBrushColor = AmazonDarkGrey,
                    startIcon = Icons.Default.Pin,
                    endIcon = null
                )

                LocationButton(onClick = {}, modifier = Modifier.weight(1f))
            }

            AmazonTextField(
                modifier = Modifier,
                text = state.zipCode,
                hint = stringResource(R.string.state_required),
                textFontColor = AmazonBlack,
                hintFontColor = AmazonGrey,
                startIconTint = AmazonMediumGrey,
                endIconTint = AmazonMediumGrey,
                focusColor = AmazonTextFieldFocusedColor,
                unfocusedColor = AmazonTextFieldUnfocusedColor,
                borderColor = AmazonDarkGrey,
                onValueChange = {
                    onAction(AddAddressActions.AddAddressState(it))
                },
                isEnabled = true,
                keyboardType = KeyboardType.Email,
                cursorBrushColor = AmazonDarkGrey,
                startIcon = Icons.Outlined.Public,
                endIcon = null
            )

            Spacer(modifier = Modifier.height(16.dp))

            AmazonTextField(
                modifier = Modifier,
                text = state.zipCode,
                hint = stringResource(R.string.locality_required),
                textFontColor = AmazonBlack,
                hintFontColor = AmazonGrey,
                startIconTint = AmazonMediumGrey,
                endIconTint = AmazonMediumGrey,
                focusColor = AmazonTextFieldFocusedColor,
                unfocusedColor = AmazonTextFieldUnfocusedColor,
                borderColor = AmazonDarkGrey,
                onValueChange = {
                    onAction(AddAddressActions.AddAddressLocality(it))
                },
                isEnabled = true,
                keyboardType = KeyboardType.Email,
                cursorBrushColor = AmazonDarkGrey,
                startIcon = Icons.Default.AddBusiness,
                endIcon = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            AmazonTextField(
                modifier = Modifier,
                text = state.zipCode,
                hint = stringResource(R.string.landmark_required),
                textFontColor = AmazonBlack,
                hintFontColor = AmazonGrey,
                startIconTint = AmazonMediumGrey,
                endIconTint = AmazonMediumGrey,
                focusColor = AmazonTextFieldFocusedColor,
                unfocusedColor = AmazonTextFieldUnfocusedColor,
                borderColor = AmazonDarkGrey,
                onValueChange = {
                    onAction(AddAddressActions.AddAddressLandmark(it))
                },
                isEnabled = true,
                keyboardType = KeyboardType.Email,
                cursorBrushColor = AmazonDarkGrey,
                startIcon = Icons.Outlined.LocationCity,
                endIcon = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            AmazonTextField(
                modifier = Modifier,
                text = state.zipCode,
                hint = stringResource(R.string.phone_number_required),
                textFontColor = AmazonBlack,
                hintFontColor = AmazonGrey,
                startIconTint = AmazonMediumGrey,
                endIconTint = AmazonMediumGrey,
                focusColor = AmazonTextFieldFocusedColor,
                unfocusedColor = AmazonTextFieldUnfocusedColor,
                borderColor = AmazonDarkGrey,
                onValueChange = {
                    onAction(AddAddressActions.AddAddressPhoneNumber(it))
                },
                isEnabled = true,
                keyboardType = KeyboardType.Email,
                cursorBrushColor = AmazonDarkGrey,
                startIcon = Icons.Outlined.Phone,
                endIcon = null
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Type of address", color = AmazonGrey)
        }
    }
}



@Preview
@Composable
private fun AddAddressScreenPreview() {
     AmazonTheme{
        AddAddressScreen(
            state = AddAddressScreenState(),
            onAction = {},
            onBackClick = {}
        )
    }
}

@Composable
fun LocationButton(onClick: () -> Unit,modifier: Modifier) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF007BFF) // Change to preferred color
        ),
        shape = RoundedCornerShape(12.dp), // Rounded corners
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(70.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.MyLocation, // GPS icon
            contentDescription = "GPS Icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp)) // Space between icon & text
        Text(
            text = "Use My Location",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

