plugins {
    alias(libs.plugins.uber.android.feature.ui)
}

android {
    namespace = "com.sougata.shopping.presentation"
}

dependencies {

    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(libs.androidx.material.icons.extended.v176)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(projects.shopping.domain)
    //implementation("com.razorpay:checkout:1.6.40")
}