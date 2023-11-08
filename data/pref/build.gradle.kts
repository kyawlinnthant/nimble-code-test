@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
    id("com.kyawlinnthant.hilt")
    id("com.kyawlinnthant.datastore.preferences")
}

android {
    namespace = "com.kyawlinnthant.pref"
    defaultConfig {
        testInstrumentationRunner = "com.kyawlinnthant.PrefTestRunner"
    }
}
dependencies {
    api(project(":cores:dispatchers"))
    api(project(":testrule"))
    implementation(libs.androidx.encrypted.preferences)
}
