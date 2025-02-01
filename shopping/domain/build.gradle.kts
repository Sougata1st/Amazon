plugins {
    alias(libs.plugins.uber.jvm.library)
}

dependencies{
    implementation(libs.kotlinx.coroutines.core)
    implementation(projects.core.domain)
}
