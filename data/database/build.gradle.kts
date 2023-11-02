@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
    id("com.kyawlinnthant.hilt")
    id("com.kyawlinnthant.database.room")
}

android {
    namespace = "com.kyawlinnthant.database"
    buildFeatures {
        buildConfig = true
    }
    defaultConfig{
        buildConfigField(
            type = "String",
            name = "BASE_URL",
            value = "\"https://survey-api.nimblehq.co/api/v1/\""
        )

    }
}
dependencies {
    api(project(":cores:dispatchers"))
}