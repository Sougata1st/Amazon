pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "Amazon"
include(":app")
include(":auth:presentation")
include(":auth:data")
include(":auth:domain")
include(":core:presentation:designSystem")
include(":core:presentation:ui")
include(":core:domain")
include(":core:data")
include(":shopping:data")
include(":shopping:domain")
include(":shopping:presentation")
include(":shopping:database")
