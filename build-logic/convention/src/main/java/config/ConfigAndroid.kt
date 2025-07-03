package config

import com.android.build.api.dsl.CommonExtension
import util.ConstantLibs
import org.gradle.api.JavaVersion.VERSION_21
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = ConstantLibs.MAX_SDK_VERSION
        namespace = (if (project.name == "app") ConstantLibs.BASE_NAME
        else "${ConstantLibs.BASE_NAME}.${project.path.replace(":", ".").substring(1)}")

        defaultConfig {
            minSdk = ConstantLibs.MIN_SDK_VERSION
        }

        buildFeatures {
            buildConfig = true
        }

        compileOptions {
            sourceCompatibility = VERSION_21
            targetCompatibility = VERSION_21
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JVM_21)
            freeCompilerArgs.add(ConstantLibs.FREE_COMPILER)
        }
    }
}