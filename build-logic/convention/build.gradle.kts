plugins {
    `kotlin-dsl`
}

group = "com.sougata.uber.buildlogic"

dependencies{
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin{
    plugins {
        register("androidApplication"){
            id = "uber.andoid.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationComposeConventionPlugin"){
            id="uber.android.application.compose"
            implementationClass ="AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibraryConventionPlugin"){
            id = "uber.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryComposeConventionPlugin"){
            id = "uber.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUiConventionPlugin"){
            id = "uber.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("androidRoomConventionPlugin"){
            id = "uber.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmKtorConventionPlugin"){
            id ="uber.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }
        register("jvmLibraryConventionPlugin"){
            id = "uber.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}