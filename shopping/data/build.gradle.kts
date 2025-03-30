plugins {
    alias(libs.plugins.uber.android.library)
    alias(libs.plugins.uber.jvm.ktor)
}

android {
    namespace = "com.sougata.shopping.data"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.shopping.domain)
    implementation(projects.shopping.database)
    implementation(libs.bundles.koin)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.8") // Use the latest version
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.8")
    //implementation("com.razorpay:checkout:1.6.40")
}