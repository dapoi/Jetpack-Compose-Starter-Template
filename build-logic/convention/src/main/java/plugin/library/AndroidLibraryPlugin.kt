package plugin.library

import com.android.build.api.dsl.LibraryExtension
import config.configAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import util.Dependencies.testDependencies
import util.ProjectConstants.MAX_SDK_VERSION
import util.alias
import util.libs

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.alias(libs.plugins.android.library)

            extensions.configure<LibraryExtension> {
                configAndroid(this)
                testOptions {
                    targetSdk = MAX_SDK_VERSION
                    unitTests.isReturnDefaultValues = true
                    unitTests.isIncludeAndroidResources = true
                }
                lint.targetSdk = MAX_SDK_VERSION
            }

            testDependencies()
        }
    }
}