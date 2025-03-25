package com.sougata.amazon

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sougata.auth.presentation.forgot_pass.change_pass.ChangePassScreenRoot
import com.sougata.auth.presentation.forgot_pass.send_and_verify_otp.SendOtpScreenRoot
import com.sougata.auth.presentation.intro.IntroScreenRoot
import com.sougata.auth.presentation.login.LoginScreenRoot
import com.sougata.auth.presentation.register.RegisterScreenRoot
import com.sougata.shopping.presentation.addAdress.AddAddressScreenRoot
import com.sougata.shopping.presentation.filter.FilterScreenRoot
import com.sougata.shopping.presentation.filterResult.FilterResultScreenRoot
import com.sougata.shopping.presentation.homeRoot.HomeScreenRoot
import kotlinx.serialization.Serializable

@Composable
fun NavigationRoot(
    navHostController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn) ShopRoutes.HomeScreen else AuthRoutes.IntroScreen
    ) {
        authGraph(navHostController)
        shopGraph(navHostController)
    }
}

private fun NavGraphBuilder.authGraph(navHostController: NavHostController) {
    composable<AuthRoutes.IntroScreen> {
        IntroScreenRoot(loginClicked = {
            navHostController.navigate(AuthRoutes.LoginScreen)
        }, registerClicked = {
            navHostController.navigate(AuthRoutes.RegisterScreen)
        })
    }
    composable<AuthRoutes.LoginScreen> {
        LoginScreenRoot(
            onBackClick = {
                navHostController.navigateUp()
            },
            forgotPassClicked = {
                navHostController.navigate(AuthRoutes.OtpScreen)
            },
            onLoginSuccess = {
                navHostController.navigate(ShopRoutes.HomeScreen) {
                    popUpTo(AuthRoutes.IntroScreen) {
                        inclusive = true
                    }
                }
            }
        )
    }
    composable<AuthRoutes.RegisterScreen> {
        RegisterScreenRoot(
            onBackClick = { navHostController.navigateUp() },
            onSuccessfulRegistration = {
                navHostController.navigate(AuthRoutes.LoginScreen) {
                    popUpTo(AuthRoutes.IntroScreen) {
                        inclusive = true
                    }
                }
            })
    }
    composable<AuthRoutes.OtpScreen> {
        SendOtpScreenRoot(
            onBackClick = {
                navHostController.navigateUp()
            },
            navigateToChangePass = {
                navHostController.navigate(AuthRoutes.ChangePasswordScreen(it))
            }
        )
    }
    composable<AuthRoutes.ChangePasswordScreen> {
        ChangePassScreenRoot(
            onBackClick = { navHostController.navigateUp() },
            onPassChange = {
                navHostController.navigate(AuthRoutes.LoginScreen) {
                    popUpTo(AuthRoutes.IntroScreen) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

private fun NavGraphBuilder.shopGraph(navHostController: NavHostController) {
    composable<ShopRoutes.HomeScreen> {
        HomeScreenRoot(
            filterClicked = {
                navHostController.navigate(ShopRoutes.FilterScreen)
            },
            addAddressClicked = {
                navHostController.navigate(ShopRoutes.AddAddressScreen)
                Log.d("Sougata","Navigated")
            },
            showAddressesClicked = { }
        )
    }

    composable<ShopRoutes.FilterScreen> {
        FilterScreenRoot(
            onBackClick = { navHostController.navigateUp() },
            navigateToFilterResultScreen = { category, sortBy, sortDir, priceRange ->
                navHostController.navigate(
                    ShopRoutes.FilterResultScreen(
                        category,
                        sortBy,
                        sortDir,
                        priceRange.start.toInt(),
                        priceRange.endInclusive.toInt()
                    )
                ) {
                    popUpTo(ShopRoutes.FilterScreen) {
                        inclusive = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
    }

    composable<ShopRoutes.AddAddressScreen> {
        AddAddressScreenRoot(onBackClick = { navHostController.navigateUp() })
    }

    composable<ShopRoutes.FilterResultScreen> {
        FilterResultScreenRoot()
    }
}

@Serializable
sealed class AuthRoutes {
    @Serializable
    data object IntroScreen : AuthRoutes()

    @Serializable
    data object LoginScreen : AuthRoutes()

    @Serializable
    data object RegisterScreen : AuthRoutes()

    @Serializable
    data class ChangePasswordScreen(val email: String) : AuthRoutes()

    @Serializable
    data object OtpScreen : AuthRoutes()
}

@Serializable
sealed class ShopRoutes {
    @Serializable
    data object HomeScreen : ShopRoutes()

    @Serializable
    data object FilterScreen : ShopRoutes()

    @Serializable
    data class FilterResultScreen(
        val category: String,
        val sortBy: String,
        val sortDirection: String,
        val lowerPriceBound: Int,
        val upperPriceBound: Int
    ) : ShopRoutes()

    @Serializable
    data object AddAddressScreen : ShopRoutes()
}



