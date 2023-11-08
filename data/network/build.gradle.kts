import java.util.Properties
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
    id("com.kyawlinnthant.hilt")
    id("com.kyawlinnthant.network.retrofit")
}

android {
    namespace = "com.kyawlinnthant.network"
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        buildConfigField(
            type = "String",
            name = "BASE_URL",
            value = "\"https://survey-api.nimblehq.co/api/v1/\""
        )
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField(
            type = "String",
            name = "CLIENT_ID",
            value = "\"${properties.getProperty("CLIENT_ID")}\""
        )
        buildConfigField(
            type = "String",
            name = "CLIENT_SECRET",
            value = "\"${properties.getProperty("CLIENT_SECRET")}\""
        )
    }
}
dependencies {
    api(project(":cores:dispatchers"))
    api(project(":data:pref"))
    api(project(":cores:util"))
}
