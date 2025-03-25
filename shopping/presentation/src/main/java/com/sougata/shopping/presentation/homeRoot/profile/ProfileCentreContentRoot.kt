package com.sougata.shopping.presentation.homeRoot.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.sougata.core.presentation.designsystem.AmazonBlack
import com.sougata.core.presentation.designsystem.AmazonGrey
import com.sougata.core.presentation.designsystem.AmazonTheme
import com.sougata.core.presentation.designsystem.AmazonWhite
import com.sougata.core.presentation.designsystem.AmazonYellow
import com.sougata.core.presentation.designsystem.components.AmazonActionButton
import com.sougata.shopping.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileCentreContentRoot(
    addAddressClicked:() -> Unit,
    showAddressesClicked :() -> Unit,
    viewModel: ProfileScreenViewModel = koinViewModel()
) {
    ProfileScreen(
        state = viewModel.state,
        onAction = viewModel::onAction,
        addAddressClicked = addAddressClicked,
        showAddressesClicked = showAddressesClicked
    )
}

@Composable
private fun ProfileScreen(
    addAddressClicked:() -> Unit,
    showAddressesClicked :() -> Unit,
    state: ProfileScreenState,
    onAction: (ProfileScreenActions) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AmazonWhite)
    ) {

        ProfileSection()

        Text(
            color = AmazonBlack,
            text = "John Doe",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )

        AddressBar(text = "Add an address", onClick = { addAddressClicked() })
        AddressBar(text = "View all addresses", onClick = { showAddressesClicked()})

        AmazonActionButton(
            text = "Logout",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isLoading = false,
            isEnabled = true,
            containerColor = AmazonYellow,
            contentColor = AmazonBlack,
            onClick = {
                onAction(ProfileScreenActions.SignOutClicked)
            },
            progressbarSize = 30.dp
        )
    }

}


@Preview
@Composable
private fun ProfileScreenPreview() {
    AmazonTheme {
        ProfileScreen(
            state = ProfileScreenState(),
            onAction = {},
            addAddressClicked = {},
            showAddressesClicked = {}
        )
    }
}


@Composable
private fun AddressBar(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = { onClick() })
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn, // Location icon
            contentDescription = "Location",
            tint = Color.Red,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f), // Allows text to expand
            textAlign = TextAlign.Center
        )

        Icon(
            imageVector = Icons.Default.ArrowForward, // Arrow icon
            contentDescription = "Navigate",
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
    }
}


@Composable
fun ProfileSection() {
    var expanded by remember { mutableStateOf(false) } // Controls dropdown visibility

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            //TODO viewModel.uploadProfileImage(context, it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)) // Curved bottom
            .background(AmazonGrey.copy(alpha = 0.2f)), // Amazon Yellow
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Black, CircleShape)
                .clickable { expanded = true } // Open dropdown on click
        ) {
            AsyncImage(
                model = "https://your-image-url.com", // Replace with actual image URL
                contentDescription = "Profile Picture",
                placeholder = painterResource(id = R.drawable.person), // Placeholder image
                error = painterResource(id = R.drawable.person), // Error case fallback
                modifier = Modifier.fillMaxSize()
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }, // Closes when tapped outside
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(
                text = { Text("Choose from Gallery") },
                onClick = {
                    expanded = false
                    // Handle gallery selection
                    galleryLauncher.launch("image/*")
                }
            )
        }
    }
}