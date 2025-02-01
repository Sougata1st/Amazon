package com.sougata.auth.presentation.forgot_pass.change_pass

data class ChangePassState(
    val isChangingPass: Boolean = false,
    val pass: String = "",
    val confirmPass: String = ""
)