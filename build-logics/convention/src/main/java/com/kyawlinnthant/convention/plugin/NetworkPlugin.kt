import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class NetworkPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val network = libs.findBundle("network").get()
            val mock = libs.findLibrary("mock-web-server").get()
            dependencies {
                add("implementation",network)
                add("androidTestImplementation",mock)
            }
        }
    }
}