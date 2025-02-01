package com.sougata.auth.presentation.register

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sougata.auth.presentation.R
import com.sougata.auth.presentation.register.components.PassStateEnum
import com.sougata.auth.presentation.util.isValidEmail
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonDarkGrey
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonMediumGrey
import com.sougata.core.presentation.designsystem.AmazonTextFieldFocusedColor
import com.sougata.core.presentation.designsystem.AmazonTextFieldUnfocusedColor
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.AmazonYellow
import com.sougata.core.presentation.designsystem.components.AmazonActionButton
import com.sougata.core.presentation.designsystem.components.AmazonScaffold
import com.sougata.core.presentation.designsystem.components.AmazonSecureTextField
import com.sougata.core.presentation.designsystem.components.AmazonTextField
import com.sougata.core.presentation.designsystem.components.AmazonToolbar
import com.sougata.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreenRoot(
    viewModel: RegisterViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onSuccessfulRegistration: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    ObserveAsEvents(viewModel.events) {
        when(it){
            is RegisterEvent.RegisterError -> {
                keyboardController?.hide()
                Toast.makeText(context, it.error.asString(context), Toast.LENGTH_SHORT).show()
            }
            RegisterEvent.RegisterSuccess -> {
                keyboardController?.hide()
                Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
                onSuccessfulRegistration()
            }
        }
    }
    RegisterScreen(
        state = viewModel.state, onAction = viewModel::onAction, onBackClick = onBackClick
    )
}

@Composable
private fun RegisterScreen(
    state: RegisterState, onAction: (RegisterAction) -> Unit, onBackClick: () -> Unit
) {
    AmazonScaffold(modifier = Modifier.statusBarsPadding(), topBar = {
        AmazonToolbar(showBackBtn = true,
            gradientColors = listOf(Color.Transparent, Color.Transparent),
            showSearchBar = false,
            onBackClick = {
                onBackClick()
            })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.sign_in_or_create_account),
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        fontSize = 24.sp
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth()
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
                            onAction(RegisterAction.EnteredEmail(it))
                        },
                        isEnabled = true,
                        keyboardType = KeyboardType.Email,
                        cursorBrushColor = AmazonDarkGrey,
                        startIcon = Icons.Default.Email,
                        endIcon = null
                    )

                    Spacer(Modifier.height(5.dp))

                    Text(stringResource(R.string.enter_your_password))
                    AmazonSecureTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.pass,
                        hint = stringResource(R.string.password),
                        textFontColor = AmazonBlack,
                        hintFontColor = AmazonGrey,
                        startIconTint = AmazonMediumGrey,
                        endIconTint = AmazonMediumGrey,
                        focusColor = AmazonTextFieldFocusedColor,
                        unfocusedColor = AmazonTextFieldUnfocusedColor,
                        borderColor = AmazonDarkGrey,
                        onValueChange = {
                            onAction(RegisterAction.EnteredPass(it))
                        },
                        isEnabled = true,
                        keyboardType = KeyboardType.Email,
                        cursorBrushColor = AmazonDarkGrey,
                        startIcon = Icons.Default.Lock,
                    )

                    Spacer(Modifier.height(5.dp))

                    Text("Re-enter your password")
                    AmazonSecureTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.confirmPass,
                        hint = stringResource(R.string.password),
                        textFontColor = AmazonBlack,
                        hintFontColor = AmazonGrey,
                        startIconTint = AmazonMediumGrey,
                        endIconTint = AmazonMediumGrey,
                        focusColor = AmazonTextFieldFocusedColor,
                        unfocusedColor = AmazonTextFieldUnfocusedColor,
                        borderColor = AmazonDarkGrey,
                        onValueChange = {
                            onAction(RegisterAction.EnteredConfirmPass(it))
                        },
                        isEnabled = true,
                        keyboardType = KeyboardType.Email,
                        cursorBrushColor = AmazonDarkGrey,
                        startIcon = Icons.Default.Lock,
                    )

                    Spacer(Modifier.height(15.dp))
                    Text(
                        text = when (state.passState) {
                            PassStateEnum.EMPTY -> "Please fill in the password fields."
                            PassStateEnum.MATCHES -> "Passwords match."
                            PassStateEnum.DOES_NOT_MATCH -> "Passwords do not match. Please try again."
                        }
                    )

                }

                AmazonActionButton(
                    text = when{
                        state.email.isEmpty() -> stringResource(R.string.register)
                        !state.email.isValidEmail() -> "Enter a valid Email"
                        else -> stringResource(R.string.register)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = state.isRegistering,
                    isEnabled = state.canRegister,
                    containerColor = AmazonYellow,
                    contentColor = AmazonDarkGrey,
                    onClick = {
                        onAction(RegisterAction.RegisterClicked)
                    }
                )

            }
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    AmazonTheme {
        RegisterScreen(state = RegisterState(), onAction = {}, onBackClick = {})
    }
}
