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
    androidTestImplementation(libs.google.hilt.test)
    androidTestImplementation(libs.test.android.runner)
    androidTestImplementation(libs.test.android.junit)
    androidTestImplementation(libs.unit.truth)
    testImplementation(libs.google.hilt.test)
    testImplementation(libs.test.unit.junit)
    testImplementation(libs.test.android.junit)
    testImplementation(libs.unit.truth)

}