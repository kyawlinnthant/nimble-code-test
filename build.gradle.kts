@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.kotlin).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.google.hilt).apply(false)
    alias(libs.plugins.google.kts).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.ktlint).apply(false)
    alias(libs.plugins.detekt).apply(false)
}
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }
}
//Workaround for "Expecting an expression" build error
println("")