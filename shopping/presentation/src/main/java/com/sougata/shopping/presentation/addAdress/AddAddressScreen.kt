package com.sougata.shopping.presentation.addAdress

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBusiness
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Pin
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.Public
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonDarkGrey
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonMediumGrey
import com.sougata.core.presentation.designsystem.AmazonTextFieldFocusedColor
import com.sougata.core.presentation.designsystem.AmazonTextFieldUnfocusedColor
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.AmazonYellow
import com.sougata.core.presentation.designsystem.components.AmazonActionButton
import com.sougata.core.presentation.designsystem.components.AmazonAddressTabItem
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



            AmazonTextField(
                modifier = Modifier,
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

            Spacer(modifier = Modifier.height(16.dp))

            AmazonTextField(
                modifier = Modifier,
                text = state.state,
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
                text = state.locality,
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
                text = state.landmark,
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
                text = state.phoneNumber,
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

            var selectedTab by rememberSaveable { mutableIntStateOf(1) }

            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                AmazonAddressTabItem(
                    selectedTab = selectedTab,
                    text = "Home",
                    imageVector = if (selectedTab == 1) Icons.Filled.Home else Icons.Outlined.Home,
                    position = 1,
                    onClick = {
                        selectedTab = it
                        AddAddressActions.AddAddressAddressType(AddressTypes.HOME.name)
                    }
                )
                AmazonAddressTabItem(
                    selectedTab = selectedTab,
                    text = "Office",
                    imageVector = if (selectedTab == 1) Icons.Filled.Business else Icons.Outlined.Business,
                    position = 2,
                    onClick = {
                        selectedTab = it
                        AddAddressActions.AddAddressAddressType(AddressTypes.OFFICE.name)
                    }
                )
                AmazonAddressTabItem(
                    selectedTab = selectedTab,
                    text = "Other",
                    imageVector = if (selectedTab == 3) Icons.Filled.LocationOn else Icons.Outlined.LocationOn,
                    position = 3,
                    onClick = {
                        selectedTab = it
                        AddAddressActions.AddAddressAddressType(AddressTypes.OTHER.name)
                    }
                )
            }
            Spacer(Modifier.height(10.dp))
            AmazonActionButton(
                text = "Save address",
                modifier = Modifier.fillMaxWidth(),
                isLoading = state.isSavingAddress,
                isEnabled = true,
                containerColor = AmazonYellow,
                contentColor = AmazonBlack,
                onClick = {
                    AddAddressActions.AddAddressClicked
                },
                progressbarSize = 20.dp
            )
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

