package plugin.module

import utils.ConstantLibs.coreModules
import utils.alias
import utils.implementation
import utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.convention.android.library)
                alias(libs.plugins.convention.compose.library)
                alias(libs.plugins.convention.navigation)
            }

            dependencies {
                coreModules.forEach { module ->
                    implementation(project(module))
                }
            }
        }
    }
}