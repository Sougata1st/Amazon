package com.sougata.auth.presentation.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.R
import com.sougata.core.presentation.designsystem.components.AmazonActionButton
import com.sougata.core.presentation.designsystem.components.AmazonGradient
import com.sougata.core.presentation.designsystem.components.AmazonScaffold
import com.sougata.core.presentation.designsystem.components.AmazonToolbar

@Composable
fun IntroScreenRoot(
    loginClicked: () -> Unit ,
    registerClicked: () -> Unit
) {
    IntroScreen(
        onAction = {
            when(it){
                IntroAction.OnLoginClicked -> {
                    loginClicked()
                }
                IntroAction.OnRegisterClicked -> {
                    registerClicked()
                }
            }
        }
    )
}


@Composable
private fun SignInToAccount() {
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = stringResource(com.sougata.auth.presentation.R.string.sign_in_to_your_account),
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                fontSize = 18.sp
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(stringResource(com.sougata.auth.presentation.R.string.view_your_wish_list))
                Text(stringResource(com.sougata.auth.presentation.R.string.find_and_render_past_purchases))
                Text(stringResource(com.sougata.auth.presentation.R.string.track_your_purchases))
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun IntroScreenPreview() {
    AmazonTheme {
        AmazonGradient {
            IntroScreen(onAction = {})
        }
    }
}


@Composable
private fun IntroScreen(
    onAction: (IntroAction) -> Unit,
) {
    AmazonScaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            AmazonToolbar(
                showBackBtn = false,
                gradientColors = listOf(Color.Transparent, Color.Transparent),
                showSearchBar = false,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.amazon_logo), // Reference the XML drawable
                        contentDescription = "Amazon Logo",
                        modifier = Modifier
                            .width(230.dp)
                            .aspectRatio(300f / 90f),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    SignInToAccount()
                }

                item {
                    AmazonActionButton(
                        text = stringResource(com.sougata.auth.presentation.R.string.already_a_customer_sign_in),
                        modifier = Modifier.fillMaxWidth(),
                        isLoading = false,
                        isEnabled = true,
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        onClick = {
                            onAction(IntroAction.OnLoginClicked)
                        }
                    )
                }

                item {
                    AmazonActionButton(
                        text = stringResource(com.sougata.auth.presentation.R.string.new_to_amazon_in_create_an_account),
                        modifier = Modifier.fillMaxWidth(),
                        isLoading = false,
                        isEnabled = true,
                        containerColor = MaterialTheme.colorScheme.surfaceContainer.copy(
                            alpha = 0.2f
                        ),
                        contentColor = MaterialTheme.colorScheme.onBackground,
                        onClick = {
                            onAction(IntroAction.OnRegisterClicked)
                        }
                    )
                }
            }
        }
    }
}

