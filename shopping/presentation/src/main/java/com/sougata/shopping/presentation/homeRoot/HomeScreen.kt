package com.sougata.shopping.presentation.homeRoot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.AmazonYellow
import com.sougata.core.presentation.designsystem.components.AmazonProductItem
import com.sougata.core.presentation.designsystem.components.AmazonScaffold
import com.sougata.core.presentation.designsystem.components.AmazonToolbar
import com.sougata.shopping.presentation.homeRoot.cart.CartCentreContentScreenRoot
import com.sougata.shopping.presentation.homeRoot.home.HomeCentreContentRoot
import com.sougata.shopping.presentation.homeRoot.home.HomeScreenViewModel
import com.sougata.shopping.presentation.homeRoot.profile.ProfileCentreContentRoot
import org.koin.androidx.compose.koinViewModel


@Composable
fun HomeScreenRoot(
    filterClicked: () -> Unit,
    viewModel: HomeScreenViewModel = koinViewModel()
) {

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    AmazonScaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            AmazonToolbar(showBackBtn = false,
                gradientColors = listOf(
                    AmazonGrey.copy(alpha = 0.2f),
                    AmazonGrey.copy(alpha = 0.2f)
                ),
                showSearchBar = false,
                showCustomTitle = true,
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = {
                            filterClicked()
                        }) {
                            Icon(imageVector = Icons.Filled.FilterAlt, contentDescription = null)
                        }
                        Text(text = "Limited Time Deals", fontSize = 20.sp)
                    }


                })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        //navigation
                    },
                    icon = {
                        if (selectedIndex == 0) {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = null)
                        } else {
                            Icon(imageVector = Icons.Outlined.Home, contentDescription = null)
                        }
                    },
                    label = {
                        Text(text = "Home")
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = AmazonBlack,
                        selectedTextColor = AmazonBlack,
                        unselectedIconColor = AmazonGrey,
                        unselectedTextColor = AmazonGrey,
                        disabledIconColor = AmazonGrey,
                        disabledTextColor = AmazonGrey,
                        selectedIndicatorColor = AmazonYellow
                    )
                )

                NavigationBarItem(
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        //navigation
                    },
                    icon = {
                        BadgedBox(
                           badge = {
                               if (viewModel.state.cartCount > 0){
                                   Badge{
                                       Text(text = viewModel.state.cartCount.toString())}
                               }
                           }
                        ) {
                            if (selectedIndex == 1) {
                                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = null)
                            } else {
                                Icon(
                                    imageVector = Icons.Outlined.ShoppingCart,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    label = {
                        Text(text = "Cart")
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = AmazonBlack,
                        selectedTextColor = AmazonBlack,
                        unselectedIconColor = AmazonGrey,
                        unselectedTextColor = AmazonGrey,
                        disabledIconColor = AmazonGrey,
                        disabledTextColor = AmazonGrey,
                        selectedIndicatorColor = AmazonYellow
                    )
                )

                NavigationBarItem(
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        //navigation
                    },
                    icon = {
                        if (selectedIndex == 2) {
                            Icon(imageVector = Icons.Filled.ShoppingBag, contentDescription = null)
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.ShoppingBag,
                                contentDescription = null
                            )
                        }
                    },
                    label = {
                        Text(text = "Orders")
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = AmazonBlack,
                        selectedTextColor = AmazonBlack,
                        unselectedIconColor = AmazonGrey,
                        unselectedTextColor = AmazonGrey,
                        disabledIconColor = AmazonGrey,
                        disabledTextColor = AmazonGrey,
                        selectedIndicatorColor = AmazonYellow
                    )
                )

                NavigationBarItem(
                    selected = selectedIndex == 3,
                    onClick = {
                        selectedIndex = 3
                        //navigation
                    },
                    icon = {
                        if (selectedIndex == 3) {
                            Icon(imageVector = Icons.Filled.Person, contentDescription = null)
                        } else {
                            Icon(imageVector = Icons.Outlined.Person, contentDescription = null)
                        }
                    },
                    label = {
                        Text(text = "Profile")
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = AmazonBlack,
                        selectedTextColor = AmazonBlack,
                        unselectedIconColor = AmazonGrey,
                        unselectedTextColor = AmazonGrey,
                        disabledIconColor = AmazonGrey,
                        disabledTextColor = AmazonGrey,
                        selectedIndicatorColor = AmazonYellow
                    )
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(paddingValues = it)
                .fillMaxSize()
                .background(color = AmazonGrey.copy(alpha = .2f))
        ) {
            CenterContent(index = selectedIndex)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    AmazonTheme {
        HomeScreenRoot(filterClicked = {})
    }
}

@Preview
@Composable
private fun ProductItemPrev() {
    AmazonProductItem(
        imageUrl = "kbdc",
        brandName = "Adidas shoes",
        category = "Clothes",
        rating = "5",
        noOfRating = "768",
        mrp = "1000",
        discountPercentage = "15",
        actualPrice = "800",
        isAddingToCart = false
    ) { }
}


@Composable
fun CenterContent(index: Int) {
    when (index) {
        0 -> {
            HomeCentreContentRoot()
        }

        1 -> {
            CartCentreContentScreenRoot()
        }

        2 -> {
            Text("Sougata")
        }

        3 -> {
            ProfileCentreContentRoot()
        }
    }
}
