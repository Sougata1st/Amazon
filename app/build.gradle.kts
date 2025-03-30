plugins {
    alias(libs.plugins.uber.android.application.compose)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sougata.amazon"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(projects.core.data)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.androidx.security.crypto)


    implementation(projects.core.presentation.ui)
    implementation(projects.core.presentation.designSystem)
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
    implementation(projects.auth.data)
    implementation(projects.auth.presentation)
    implementation(projects.amazon.shopping.database)
    implementation(projects.shopping.data)
    implementation(projects.shopping.domain)
    implementation(projects.shopping.presentation)
    implementation(projects.shopping.database)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.navigation.compose.v285)
    implementation(libs.bundles.koin)
    implementation(libs.checkout)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}