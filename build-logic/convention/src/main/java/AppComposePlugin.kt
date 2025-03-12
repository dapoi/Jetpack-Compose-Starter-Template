import com.android.build.api.dsl.ApplicationExtension
import com.project.compose.build_logic.convention.alias
import com.project.compose.build_logic.convention.configCompose
import com.project.compose.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AppComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.alias(libs.plugins.android.application)
            val extension = extensions.getByType<ApplicationExtension>()
            configCompose(extension)
        }
    }
}