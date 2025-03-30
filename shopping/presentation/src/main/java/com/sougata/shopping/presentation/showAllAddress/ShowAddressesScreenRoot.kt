package com.sougata.shopping.presentation.showAllAddress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.components.AmazonAddressCard
import com.sougata.core.presentation.designsystem.components.AmazonScaffold
import com.sougata.core.presentation.designsystem.components.AmazonToolbar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShowAddressScreenRoot(
    viewModel: ShowAddressViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    ShowAddressScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        onBackClick = onBackClick
    )
}

@Composable
private fun ShowAddressScreen(
    onBackClick: () -> Unit,
    state: ShowAddressState,
    onAction: (ShowAddressActions) -> Unit
) {
    AmazonScaffold(
        topBar = {
            AmazonToolbar(
                showCustomTitle = true,
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Show Addresses",
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
        LazyColumn(
            modifier = Modifier.fillMaxSize()
            .padding(it)) {
            items(state.addressList) {
                AmazonAddressCard(
                    locality = it.locality,
                    landmark = it.landmark,
                    state = it.state,
                    zipCode = it.zipCode,
                    phoneNumber = it.phoneNumber,
                    addressType = it.addressType,
                    onDelete = {
                        onAction(ShowAddressActions.DeleteClicked(it.id))
                    },
                    isDeleting =  state.addressAddRemove.addressId == it.id && state.addressAddRemove.isDeleting
                )
            }
        }
    }
}

@Preview
@Composable
fun ShowAddressScreenPreview() {
    AmazonTheme {
        ShowAddressScreen(state = ShowAddressState(),
            onAction = {}, onBackClick = {})
    }
}
