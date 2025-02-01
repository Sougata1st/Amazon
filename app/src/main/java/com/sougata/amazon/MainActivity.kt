package com.sougata.amazon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.components.AmazonGradient
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AmazonTheme {
                AmazonGradient {
                    val navHostController = rememberNavController()
                    val isLoggedIn = viewModel.authState.isLoggedIn
                    val isCheckingAuth = viewModel.authState.isCheckingAuth
                    if (!isCheckingAuth){
                        NavigationRoot(navHostController, isLoggedIn)
                    }

                }
            }
        }
    }
}

