package config

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion.VERSION_21
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import util.ConstantLibs.BASE_NAME
import util.ConstantLibs.MAX_SDK_VERSION
import util.ConstantLibs.MIN_SDK_VERSION
import util.ConstantLibs.freeCompiler

internal fun Project.configAndroid(extension: Any) {
    when (extension) {
        is ApplicationExtension -> extension.configure(this)
        is LibraryExtension -> extension.configure(this)
    }
    configureKotlin()
}

private fun ApplicationExtension.configure(project: Project) = apply {
    compileSdk = MAX_SDK_VERSION
    namespace = if (project.name == "app") BASE_NAME
    else "$BASE_NAME.${project.path.replace(":", ".").substring(1)}"

    defaultConfig {
        minSdk = MIN_SDK_VERSION
    }

    buildFeatures { buildConfig = true }
    compileOptions {
        sourceCompatibility = VERSION_21
        targetCompatibility = VERSION_21
    }
}

private fun LibraryExtension.configure(project: Project) = apply {
    compileSdk = MAX_SDK_VERSION
    namespace = "$BASE_NAME.${project.path.replace(":", ".").substring(1)}"

    defaultConfig {
        minSdk = MIN_SDK_VERSION
    }

    buildFeatures { buildConfig = true }
    compileOptions {
        sourceCompatibility = VERSION_21
        targetCompatibility = VERSION_21
    }
}

private fun Project.configureKotlin() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JVM_21)
            freeCompilerArgs.addAll(freeCompiler)
        }
    }
}