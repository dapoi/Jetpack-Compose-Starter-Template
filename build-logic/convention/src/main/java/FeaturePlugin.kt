
import com.project.compose.build_logic.convention.ConstantLibs.coreModules
import com.project.compose.build_logic.convention.alias
import com.project.compose.build_logic.convention.implementation
import com.project.compose.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.base.library)
                alias(libs.plugins.base.navigation)
                alias(libs.plugins.base.library.compose)
            }

            dependencies {
                coreModules.forEach { module -> implementation(project(module)) }
            }
        }
    }
}