plugins {
    alias(libs.plugins.uber.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sougata.auth.data"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(libs.bundles.ktor)
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}