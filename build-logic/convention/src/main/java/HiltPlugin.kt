import com.project.compose.build_logic.convention.ConstantLibs.KSP
import com.project.compose.build_logic.convention.alias
import com.project.compose.build_logic.convention.implementation
import com.project.compose.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltPlugin : Plugin<Project> {
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