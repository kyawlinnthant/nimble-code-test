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
include(":data:pref")
include(":data:database")
include(":data:network")
include(":cores:navigation")
include(":cores:theme")
include(":cores:util")
include(":cores:dispatchers")
include(":features:home:presentation")
include(":features:home:domain")
include(":features:home:data")
include(":features:auth:presentation")
include(":features:auth:domain")
include(":features:auth:data")
include(":testrule")
