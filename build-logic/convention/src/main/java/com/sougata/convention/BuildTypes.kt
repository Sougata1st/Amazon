package com.sougata.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*,*,*,*,*,*>,
    extensionType: ExtensionType
){
    commonExtension.run {
        buildFeatures{
            buildConfig = true
        }
        val mapsApiKey = gradleLocalProperties(rootDir,providers).getProperty("MAPS_API_KEY")

        when(extensionType){
            ExtensionType.APPLICATION -> extensions.configure<ApplicationExtension>{
                buildTypes{
                    debug {
                        configureDebugBuildType(mapsApiKey)
                    }
                    release {
                        configureReleaseBuildType(
                            commonExtension,
                            mapsApiKey
                        )
                    }
                }
            }
            ExtensionType.LIBRARY -> extensions.configure<LibraryExtension> {
                buildTypes{
                    debug {
                        configureDebugBuildType(mapsApiKey)
                    }
                    release {
                        configureReleaseBuildType(
                            commonExtension,
                            mapsApiKey
                        )
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(mapsApiKey: String) {
    buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
    buildConfigField("String", "BASE_URL","\"https://e-store-user-service.onrender.com\"")
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *,*>,
    mapsApiKey: String
) {
    buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://e-store-user-service.onrender.com\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}