import com.android.build.api.dsl.ApplicationExtension
import com.project.compose.build_logic.convention.ConstantLibs.MAX_SDK_VERSION
import com.project.compose.build_logic.convention.ConstantLibs.resourceExcludes
import com.project.compose.build_logic.convention.alias
import com.project.compose.build_logic.convention.configAndroid
import com.project.compose.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.android.application)
                alias(libs.plugins.kotlin.android)
                alias(libs.plugins.kotlin.compose)
            }

            extensions.configure<ApplicationExtension> {
                configAndroid(this)
                defaultConfig.targetSdk = MAX_SDK_VERSION

                packaging {
                    resources {
                        resourceExcludes.forEach { excludes += it }
                    }
                }
            }
        }
    }
}