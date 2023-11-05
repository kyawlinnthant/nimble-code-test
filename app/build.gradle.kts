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
                "proguard-rules.pro",
            )
        }
    }
}
tasks.getByPath("preBuild").dependsOn("ktlintFormat")
ktlint {
    version.set(libs.versions.ktlint.version.get())
    android.set(true)
    verbose.set(true)
    ignoreFailures.set(true)
    outputColorName.set("RED")
    reporters {
        reporter(reporterType = org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(reporterType = org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
        reporter(reporterType = org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
    }
    filter {
        exclude("**/generated/**")
        include("**/*.kt", "**/*.kts")
    }
}
detekt {
    parallel = true
    allRules = true
    autoCorrect = true
    buildUponDefaultConfig = true
    source.setFrom(files(projectDir))
    config.setFrom(file("${rootProject.rootDir}/config/detekt/detekt.yml"))

    tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
        include("**/*.kt", "**/*.kts")
        exclude("**/build/**")

        jvmTarget = JavaVersion.VERSION_17.toString()

        reports {
            txt.required.set(false)
            sarif.required.set(false)
            md.required.set(false)
            html.required.set(true)
            html.outputLocation.set(file("${project.buildDir}/reports/detekt/detekt.html"))
            xml.required.set(true)
            xml.outputLocation.set(file("${project.buildDir}/reports/detekt/detekt.xml")) // It's required for Sonar
        }
    }
}
dependencies {
    implementation(project(":cores:theme"))
    implementation(project(":cores:navigation"))
    implementation(project(":features:auth:presentation"))
    implementation(project(":features:home:presentation"))
}
