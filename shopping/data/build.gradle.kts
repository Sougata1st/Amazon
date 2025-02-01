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
}