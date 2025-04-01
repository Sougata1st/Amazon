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
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
}
