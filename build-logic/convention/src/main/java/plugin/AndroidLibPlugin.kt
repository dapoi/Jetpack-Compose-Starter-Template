package plugin

import com.android.build.gradle.LibraryExtension
import utils.ConstantLibs.MAX_SDK_VERSION
import utils.alias
import config.configAndroid
import utils.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.android.library)
                alias(libs.plugins.kotlin.android)
                alias(libs.plugins.kotlin.compose)
                alias(libs.plugins.convention.hilt)
            }

            extensions.configure<LibraryExtension> {
                configAndroid(this)
                defaultConfig.targetSdk = MAX_SDK_VERSION
            }
        }
    }
}