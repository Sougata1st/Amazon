plugins {
    alias(libs.plugins.uber.android.library.compose)
}

android {
    namespace = "com.sougata.core.presentation.designsystem"
}

dependencies {

    api(libs.androidx.compose.material3)
    implementation(libs.androidx.core)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.material3)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.androidx.material.icons.extended.v176)
}