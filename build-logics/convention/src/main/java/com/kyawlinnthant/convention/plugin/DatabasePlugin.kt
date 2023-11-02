import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class DatabasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val googleKsp = "com.google.devtools.ksp"
        with(target) {
            with(pluginManager) {
                apply(googleKsp)
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            val room = libs.findBundle("room").get()
            val compiler = libs.findLibrary("room-compiler").get()

            dependencies {
                add("implementation",room)
                add("ksp",compiler)

            }
        }
    }
}