@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
    id("com.kyawlinnthant.hilt")
}

android {
    namespace = "com.kyawlinnthant.home.data"
}
dependencies {
    api(project(":data:network"))
    api(project(":data:database"))
    testImplementation(libs.mock.web.server)
}
