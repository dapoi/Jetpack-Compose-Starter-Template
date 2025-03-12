import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.project.compose.build_logic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "base.application"
            implementationClass = "AppPlugin"
        }
        register("androidApplicationCompose") {
            id = "base.application.compose"
            implementationClass = "AppComposePlugin"
        }
        register("androidApi") {
            id = "base.api"
            implementationClass = "ApiPlugin"
        }
        register("androidFeature") {
            id = "base.feature"
            implementationClass = "FeaturePlugin"
        }
        register("androidHilt") {
            id = "base.hilt"
            implementationClass = "HiltPlugin"
        }
        register("androidLibrary") {
            id = "base.library"
            implementationClass = "LibPlugin"
        }
        register("androidLibraryCompose") {
            id = "base.library.compose"
            implementationClass = "LibComposePlugin"
        }
        register("androidNav") {
            id = "base.navigation"
            implementationClass = "NavPlugin"
        }
    }
}