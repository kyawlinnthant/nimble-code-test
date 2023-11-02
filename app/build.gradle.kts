@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.kyawlinnthant.android.application")
    id("com.kyawlinnthant.compose.application")
    id("com.kyawlinnthant.hilt")
}

android {
    namespace = "com.kyawlinnthant.nimble"
    defaultConfig {
        applicationId = "com.kyawlinnthant.nimble"
        versionCode = 1
        versionName = "0.0.1" // X.Y.Z ( Major.Minor.Patch)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":cores:theme"))
    implementation(project(":cores:navigation"))
    implementation(project(":features:auth:presentation"))
    implementation(project(":features:home:presentation"))
}
