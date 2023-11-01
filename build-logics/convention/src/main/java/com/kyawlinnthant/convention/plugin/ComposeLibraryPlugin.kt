import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class ComposeLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val androidLibrary = "com.android.library"

        with(target) {
            with(pluginManager) {
                apply(androidLibrary)
            }
            extensions.configure<LibraryExtension> {
                configureCompose(this)
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val compose = libs.findBundle("androidx-compose").get()
            val composeDebug = libs.findBundle("androidx-compose-debug").get()
            val composeTest = libs.findLibrary("compose-test").get()
            dependencies {
                add("implementation",compose)
                add("debugImplementation",composeDebug)
                add("androidTestImplementation",composeTest)
            }
        }
    }
}