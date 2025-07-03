package plugin

import utils.ConstantLibs.KSP
import utils.alias
import utils.implementation
import utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltLibPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.hilt)
                alias(libs.plugins.ksp)
            }

            dependencies {
                implementation(libs.hilt.android.get())
                implementation(libs.hilt.navigation.get())
                add(KSP, libs.hilt.compiler.get())
            }
        }
    }
}