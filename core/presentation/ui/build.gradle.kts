plugins {
    alias(libs.plugins.uber.android.library.compose)
}

android {
    namespace = "com.sougata.core.presentation.ui"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designSystem)
    implementation(libs.androidx.lifecycle.runtime.compose)
}