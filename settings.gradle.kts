@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        includeBuild("build-logics")
        google()
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

rootProject.name = "nimble"
include(":app")
include(":features:auth")
include(":features:home")
include(":data:pref")
include(":data:database")
include(":data:network")
include(":cores:navigation")
include(":cores:theme")
