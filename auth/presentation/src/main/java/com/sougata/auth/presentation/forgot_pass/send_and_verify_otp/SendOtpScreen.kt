package com.sougata.auth.presentation.forgot_pass.send_and_verify_otp

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sougata.auth.presentation.R
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonDarkGrey
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonMediumGrey
import com.sougata.core.presentation.designsystem.AmazonRed
import com.sougata.core.presentation.designsystem.AmazonTextFieldFocusedColor
import com.sougata.core.presentation.designsystem.AmazonTextFieldUnfocusedColor
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.AmazonYellow
import com.sougata.core.presentation.designsystem.components.AmazonActionButton
import com.sougata.core.presentation.designsystem.components.AmazonScaffold
import com.sougata.core.presentation.designsystem.components.AmazonTextField
import com.sougata.core.presentation.designsystem.components.AmazonToolbar
import com.sougata.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun SendOtpScreenRoot(
    viewModel: SendOtpViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    navigateToChangePass: (email:String) -> Unit
) {
    val canSendOtp by viewModel.canSendOtp.collectAsStateWithLifecycle()
    val canVerifyOtp by viewModel.canVerifyOtp.collectAsStateWithLifecycle()

    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) {
        when(it){
            is SendOtpEvents.FailedToSendOtp -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    it.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
            is SendOtpEvents.FailedToVerifyOtp ->{
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    it.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
            SendOtpEvents.SentOtp -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    "Otp has been sent",
                    Toast.LENGTH_SHORT
                ).show()
            }
            SendOtpEvents.VerifiedOtp -> {
                navigateToChangePass(viewModel.state.email)
            }
        }
    }

    SendOtpScreen(
        canSendOtp = canSendOtp,
        canVerifyOtp = canVerifyOtp,
        state = viewModel.state,
        onAction = viewModel::onAction,
        onBackClick = { onBackClick() }
    )
}

@Composable
private fun SendOtpScreen(
    canSendOtp: Boolean ,
    canVerifyOtp: Boolean,
    onBackClick: () -> Unit,
    state: SendOtpState,
    onAction: (SendOtpActions) -> Unit
) {
    AmazonScaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            AmazonToolbar(
                showBackBtn = true,
                gradientColors = listOf(Color.Transparent, Color.Transparent),
                showSearchBar = false,
                onBackClick = {
                    onBackClick()
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {

                Text(stringResource(R.string.enter_your_email))
                AmazonTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.email,
                    hint = stringResource(R.string.amazon_example_com),
                    textFontColor = AmazonBlack,
                    hintFontColor = AmazonGrey,
                    startIconTint = AmazonMediumGrey,
                    endIconTint = AmazonMediumGrey,
                    focusColor = AmazonTextFieldFocusedColor,
                    unfocusedColor = AmazonTextFieldUnfocusedColor,
                    borderColor = AmazonDarkGrey,
                    onValueChange = {
                        onAction(SendOtpActions.EnteredEmail(it))
                    },
                    isEnabled = true,
                    keyboardType = KeyboardType.Email,
                    cursorBrushColor = AmazonDarkGrey,
                    startIcon = Icons.Default.Email,
                    endIcon = null
                )

                Text(
                    fontSize = 13.sp,
                    color = AmazonRed,
                    text = when {
                        state.email.isEmpty() -> ""
                        !state.isEmailValid -> "Enter a valid email"
                        else -> ""
                    }
                )


                AmazonActionButton(
                    text = "Send Otp",
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = state.isSendingOtp,
                    isEnabled = canSendOtp,
                    containerColor = AmazonYellow,
                    contentColor = AmazonDarkGrey,
                    onClick = {
                        onAction(SendOtpActions.SendOtpClicked)
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                Text("Enter your otp")
                AmazonTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.otp,
                    hint = "******",
                    textFontColor = AmazonBlack,
                    hintFontColor = AmazonGrey,
                    startIconTint = AmazonMediumGrey,
                    endIconTint = AmazonMediumGrey,
                    focusColor = AmazonTextFieldFocusedColor,
                    unfocusedColor = AmazonTextFieldUnfocusedColor,
                    borderColor = AmazonDarkGrey,
                    onValueChange = {
                        onAction(SendOtpActions.EnteredOtp(it))
                    },
                    isEnabled = true,
                    keyboardType = KeyboardType.Number,
                    cursorBrushColor = AmazonDarkGrey,
                    startIcon = Icons.Default.Lock,
                    endIcon = null
                )
                Text(text = "",fontSize = 13.sp)

                AmazonActionButton(
                    text = "Verify Otp",
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = state.isVerifyingOtp,
                    isEnabled = canVerifyOtp,
                    containerColor = AmazonYellow,
                    contentColor = AmazonDarkGrey,
                    onClick = {
                        onAction(SendOtpActions.VerifyOtpClicked)
                    }
                )

            }
        }
    }
}

@Preview
@Composable
fun SendOtpScreenPreview() {
    AmazonTheme {
        SendOtpScreen(
            state = SendOtpState(),
            onAction = {},
            onBackClick = {},
            canSendOtp = false,
            canVerifyOtp = false
        )
    }
}
