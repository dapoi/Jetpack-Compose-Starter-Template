import com.android.build.gradle.LibraryExtension
import com.project.compose.build_logic.convention.ConstantLibs.MAX_SDK_VERSION
import com.project.compose.build_logic.convention.alias
import com.project.compose.build_logic.convention.configAndroid
import com.project.compose.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class LibPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.android.library)
                alias(libs.plugins.kotlin.android)
                alias(libs.plugins.kotlin.compose)
                alias(libs.plugins.base.hilt)
            }

            extensions.configure<LibraryExtension> {
                configAndroid(this)
                defaultConfig.targetSdk = MAX_SDK_VERSION
            }
        }
    }
}