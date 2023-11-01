import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun Project.configureKotlin(
    extension: CommonExtension<*, *, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    val desugar = libs.findLibrary("desugaring").get()
    extension.apply {
        compileSdk = AppConfig.COMPILE_SDK
        defaultConfig {
            minSdk = AppConfig.MIN_SDK
        }
        compileOptions {
            sourceCompatibility = AppConfig.JAVA_VERSION
            targetCompatibility = AppConfig.JAVA_VERSION
            isCoreLibraryDesugaringEnabled = true
        }
        kotlinOptions {
            jvmTarget = AppConfig.JAVA_VERSION.toString()
        }
        dependencies {
            add("coreLibraryDesugaring", desugar)
        }
    }
}

fun CommonExtension<*, *, *, *, *>.kotlinOptions(
    block: KotlinJvmOptions.() -> Unit
) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

