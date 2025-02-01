package com.sougata.auth.presentation.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sougata.auth.presentation.R
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
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    forgotPassClicked:()-> Unit,
    onLoginSuccess: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current
    ObserveAsEvents(viewModel.events) {
        when(it){
            is LoginEvent.LoginError -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    it.error.asString(context),
                    Toast.LENGTH_SHORT
                ).show()
            }
            LoginEvent.LoginSuccess -> {
                keyboardController?.hide()
                Toast.makeText(
                    context,
                    "Login Success",
                    Toast.LENGTH_SHORT
                ).show()
                onLoginSuccess()
            }
        }
    }

    LoginScreenScreen(
        state = viewModel.state,
        onAction = {
            when(it){
                is LoginAction.ForgotPassClicked -> {
                    forgotPassClicked()
                }

                else -> {
                    Unit
                }
            }
            viewModel.onAction(it)
        },
        onBackClick = onBackClick
    )
}

@Composable
private fun LoginScreenScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit,
    onBackClick: () -> Unit
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
    ) { paddingValues ->
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
                            onAction(LoginAction.EnteredEmail(it))
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
                        text = state.password,
                        hint = stringResource(R.string.password),
                        textFontColor = AmazonBlack,
                        hintFontColor = AmazonGrey,
                        startIconTint = AmazonMediumGrey,
                        endIconTint = AmazonMediumGrey,
                        focusColor = AmazonTextFieldFocusedColor,
                        unfocusedColor = AmazonTextFieldUnfocusedColor,
                        borderColor = AmazonDarkGrey,
                        onValueChange = {
                            onAction(LoginAction.EnteredPass(it))
                        },
                        isEnabled = true,
                        keyboardType = KeyboardType.Email,
                        cursorBrushColor = AmazonDarkGrey,
                        startIcon = Icons.Default.Lock,
                    )
                }

                AmazonActionButton(
                    text = when {
                        !state.email.isValidEmail() -> "Enter a valid email"
                        state.password.isEmpty() -> "Password cannot be empty"
                        else -> stringResource(R.string.login)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = state.isLoggingIn,
                    isEnabled = state.canLogin,
                    containerColor = AmazonYellow,
                    contentColor = AmazonDarkGrey,
                    onClick = {
                        onAction(LoginAction.LoginClicked)
                    }
                )

                TermsAndConditionsText(onClick = {
                    when (it) {
                        0 -> {
                            LoginAction.ConditionsOfUseClicked
                        }

                        1 -> {
                            LoginAction.PrivacyNoticeClicked
                        }
                    }
                })

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Forgot your password?",
                        color = Color.Blue,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            onAction(LoginAction.ForgotPassClicked)
                        }
                    )
                }

            }
        }
    }
}


@Composable
fun TermsAndConditionsText(
    onClick: (Int) -> Unit,
) {
    // Build AnnotatedString with clickable portions
    val annotatedString = buildAnnotatedString {
        append("By continuing, you agree to Amazon's ")

        // Add "Conditions of Use" with clickable annotation
        pushStringAnnotation(tag = "CONDITIONS", annotation = "ConditionsOfUse")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Conditions of Use")
        }
        pop()

        append(" and ")

        // Add "Privacy Notice" with clickable annotation
        pushStringAnnotation(tag = "PRIVACY", annotation = "PrivacyNotice")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append("Privacy Notice.")
        }
        pop()
    }

    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    // Modifier for detecting tap gestures
    val pressIndicator = Modifier.pointerInput(onClick) {
        detectTapGestures { pos ->
            layoutResult.value?.let { layoutResult ->
                val offset = layoutResult.getOffsetForPosition(pos)
                annotatedString.getStringAnnotations(start = offset, end = offset)
                    .firstOrNull()?.let {
                        when (it.tag) {
                            "CONDITIONS" -> onClick(0)
                            "PRIVACY" -> onClick(1)
                        }
                    }
            }
        }
    }

    // BasicText renders the annotated string
    BasicText(
        text = annotatedString,
        modifier = pressIndicator,
        style = MaterialTheme.typography.bodyMedium
    )
}


@Preview
@Composable
fun LoginScreenRootScreenPreview() {
    AmazonTheme {
        LoginScreenScreen(
            state = (LoginState(
                email = "skar@gmail.com", password = "s"
            )),
            onAction = {

            },
            onBackClick = {}
        )
    }
}
