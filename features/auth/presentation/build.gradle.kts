@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
    id("com.kyawlinnthant.compose.library")
    id("com.kyawlinnthant.hilt")
}

android {
    namespace = "com.kyawlinnthant.auth.presentation"
}
dependencies {
    api(project(":cores:navigation"))
    api(project(":features:auth:domain"))
    api(project(":cores:theme"))
}