@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
    id("com.kyawlinnthant.hilt")
    id("com.kyawlinnthant.network.retrofit")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.kyawlinnthant.network"
}
dependencies {
    api(project(":cores:dispatchers"))
    api(project(":data:pref"))
    api(libs.serialization.json)
}