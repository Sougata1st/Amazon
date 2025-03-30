package com.sougata.shopping.presentation.homeRoot.profile

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sougata.core.domain.SessionStorage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class ProfileScreenViewModel(val sessionStorage: SessionStorage): ViewModel() {
    val state by mutableStateOf(ProfileScreenState())

    private val _eventChannel = Channel<ProfileScreenEvents>()
    val events = _eventChannel.receiveAsFlow()

    fun onAction(actions: ProfileScreenActions){
        when(actions){
            ProfileScreenActions.SignOutClicked -> {
                viewModelScope.launch {
                    sessionStorage.set(null)
                    _eventChannel.send(ProfileScreenEvents.NavigateToLogin)
                }
            }
            else -> Unit
        }
    }

    private fun uriToFile(context: Context, uri: Uri): File? {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val file = File(context.cacheDir, "upload_image.jpg")
        inputStream.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }
        return file
    }
}