plugins {
    alias(libs.plugins.uber.android.library)
    alias(libs.plugins.uber.android.room)
}

android {
    namespace = "com.sougata.shopping.database"
}
dependencies{
    implementation(projects.shopping.domain)
    implementation(libs.bundles.koin)
    implementation(projects.core.domain)
}
