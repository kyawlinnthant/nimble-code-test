import java.util.Properties
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.kyawlinnthant.android.library")
    id("com.kyawlinnthant.hilt")
}

android {
    namespace = "com.kyawlinnthant.auth.data"
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
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
    api(project(":data:network"))
    testImplementation(libs.mock.web.server)
}
