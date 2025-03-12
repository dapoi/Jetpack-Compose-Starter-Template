package com.project.compose.build_logic.convention

import com.android.build.api.dsl.CommonExtension
import com.project.compose.build_logic.convention.ConstantLibs.BASE_NAME
import com.project.compose.build_logic.convention.ConstantLibs.FREE_COMPILER
import com.project.compose.build_logic.convention.ConstantLibs.MAX_SDK_VERSION
import com.project.compose.build_logic.convention.ConstantLibs.MIN_SDK_VERSION
import org.gradle.api.JavaVersion.VERSION_17
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = MAX_SDK_VERSION
        namespace = (if (project.name == "app") BASE_NAME
        else "$BASE_NAME.${project.path.replace(":", ".").substring(1)}")

        defaultConfig {
            minSdk = MIN_SDK_VERSION
        }

        buildFeatures {
            buildConfig = true
        }

        compileOptions {
            sourceCompatibility = VERSION_17
            targetCompatibility = VERSION_17
        }
    }

    configureKotlinCompile()
}

private fun Project.configureKotlinCompile() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JVM_17)
            freeCompilerArgs.add(FREE_COMPILER)
        }
    }
}