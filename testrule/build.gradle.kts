@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("kotlin")
}
group = "com.kyawlinnthant.testrule"
dependencies {
    api(libs.coroutines.test)
    api(libs.test.unit.junit)
}
