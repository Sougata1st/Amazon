package com.sougata.shopping.presentation.homeRoot.profile

sealed interface ProfileScreenActions {
    data object ProfileClicked: ProfileScreenActions
    data object SignOutClicked: ProfileScreenActions
    data object AddAddressClicked: ProfileScreenActions
    data object ShowAllAddressesClicked: ProfileScreenActions
}