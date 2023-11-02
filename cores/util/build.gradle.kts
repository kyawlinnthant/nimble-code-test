@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
}

android {
    namespace = "com.kyawlinnthant.util"
}
dependencies {
    testImplementation(libs.test.unit.junit)
    testImplementation(libs.unit.truth)
    implementation(libs.androidx.core)
}