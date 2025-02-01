package com.sougata.auth.presentation.forgot_pass.change_pass

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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.sougata.core.presentation.designsystem.components.AmazonToolbar
import com.sougata.core.presentation.ui.ObserveAsEvents
import org.koin.androidx.compose.koinViewModel


@Composable
fun ChangePassScreenRoot(
    viewModel: ChangePassViewModel = koinViewModel(),
    onPassChange: () ->Unit,
    onBackClick: () -> Unit
) {
    val canChangePass by viewModel.canChangePass.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    ChangePassScreen(
        canChangePass = canChangePass,
        state = viewModel.state,
        onAction = viewModel::onAction,
        onBackClick = { onBackClick() }
    )
    ObserveAsEvents(viewModel.events) {
        when(it){
            is ChangePassEvents.Failure -> {
                keyboardController?.hide()
                Toast.makeText(context,it.msg.asString(context), Toast.LENGTH_SHORT).show()
            }
            ChangePassEvents.Success -> onPassChange()
        }
    }
}

@Composable
private fun ChangePassScreen(
    canChangePass:Boolean,
    onBackClick: () -> Unit,
    state: ChangePassState,
    onAction: (ChangePassActions) -> Unit
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

                Text("Enter you password")
                AmazonSecureTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.pass,
                    hint = "*****",
                    textFontColor = AmazonBlack,
                    hintFontColor = AmazonGrey,
                    startIconTint = AmazonMediumGrey,
                    endIconTint = AmazonMediumGrey,
                    focusColor = AmazonTextFieldFocusedColor,
                    unfocusedColor = AmazonTextFieldUnfocusedColor,
                    borderColor = AmazonDarkGrey,
                    onValueChange = {
                        onAction(ChangePassActions.EnteredPass(it))
                    },
                    isEnabled = true,
                    keyboardType = KeyboardType.Password,
                    cursorBrushColor = AmazonDarkGrey,
                    startIcon = Icons.Default.Lock,
                )


                Spacer(modifier = Modifier.height(10.dp))




                Text("Re enter your Password")
                AmazonSecureTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.confirmPass,
                    hint = "*****",
                    textFontColor = AmazonBlack,
                    hintFontColor = AmazonGrey,
                    startIconTint = AmazonMediumGrey,
                    endIconTint = AmazonMediumGrey,
                    focusColor = AmazonTextFieldFocusedColor,
                    unfocusedColor = AmazonTextFieldUnfocusedColor,
                    borderColor = AmazonDarkGrey,
                    onValueChange = {
                        onAction(ChangePassActions.EnteredConfirmPass(it))
                    },
                    isEnabled = true,
                    keyboardType = KeyboardType.Password,
                    cursorBrushColor = AmazonDarkGrey,
                    startIcon = Icons.Default.Lock,
                )
                Text(text = if (canChangePass){
                    ""
                }else{
                    "Password does not match"
                }, fontSize = 13.sp)

                AmazonActionButton(
                    text = "Confirm password",
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = state.isChangingPass,
                    isEnabled = canChangePass,
                    containerColor = AmazonYellow,
                    contentColor = AmazonDarkGrey,
                    onClick = {
                        onAction(ChangePassActions.ConfirmPassClicked)
                    }
                )

            }
        }
    }
}

@Preview
@Composable
fun ChangePassScreenPreview() {
    AmazonTheme {
        ChangePassScreen(
            canChangePass = true,
            onBackClick = {},
            state = ChangePassState(
                pass = "Sougata",
                confirmPass = "kar"
            )
        ) {

        }
    }
}
