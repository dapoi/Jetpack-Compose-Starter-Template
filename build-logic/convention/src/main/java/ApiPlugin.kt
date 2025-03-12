import com.project.compose.build_logic.convention.ConstantLibs.coreModules
import com.project.compose.build_logic.convention.alias
import com.project.compose.build_logic.convention.debugImplementation
import com.project.compose.build_logic.convention.implementation
import com.project.compose.build_logic.convention.libs
import com.project.compose.build_logic.convention.releaseImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class ApiPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.alias(libs.plugins.base.library)
            dependencies {
                implementation(project(coreModules[1]))
                implementation(libs.dataStorePreferences.get())
                implementation(libs.okhttp.interceptor.get())
                implementation(libs.retrofit.lib.get())
                implementation(libs.retrofit.converter.get())
                implementation(libs.timber.get())
                debugImplementation(libs.chucker.debug.get())
                releaseImplementation(libs.chucker.release.get())
            }
        }
    }
}