plugins {
    alias(libs.plugins.uber.android.library)
    alias(libs.plugins.uber.jvm.ktor)
}

android {
    namespace = "com.sougata.core.data"

}

dependencies {
    implementation(projects.core.domain)
    implementation(libs.bundles.koin)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
}
