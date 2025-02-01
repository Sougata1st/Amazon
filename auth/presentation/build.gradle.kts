plugins {
    alias(libs.plugins.uber.android.feature.ui)
}

android {
    namespace = "com.sougata.auth.presentation"

}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
}