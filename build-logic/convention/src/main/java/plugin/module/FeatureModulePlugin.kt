package plugin.module

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.Dependencies.testDependencies
import util.ProjectConstants.coreModules
import util.alias
import util.implementation
import util.libs

class FeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.convention.android.library)
                alias(libs.plugins.convention.compose.library)
                alias(libs.plugins.convention.hilt)
                alias(libs.plugins.kotlin.serialization)
                alias(libs.plugins.kotlin.parcelize)
            }

            dependencies {
                coreModules.forEach { module ->
                    implementation(project(module))
                }
                implementation(libs.hilt.navigation.get())
            }

            testDependencies()
        }
    }
}