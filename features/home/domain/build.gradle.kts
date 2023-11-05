@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
    id("com.kyawlinnthant.hilt")
}

android {
    namespace = "com.kyawlinnthant.home.domain"
}
dependencies {
    api(project(":features:home:data"))
}
