@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
    id("com.kyawlinnthant.compose.library")
    id("com.kyawlinnthant.hilt")
}

android {
    namespace = "com.kyawlinnthant.home.presentation"
}
dependencies {
    api(project(":cores:navigation"))
    api(project(":cores:theme"))
    api(project(":features:home:domain"))
    implementation(libs.compose.coil)
}
