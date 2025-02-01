package com.sougata.auth.presentation.intro

sealed interface IntroAction {
    data object OnLoginClicked: IntroAction
    data object OnRegisterClicked: IntroAction
}