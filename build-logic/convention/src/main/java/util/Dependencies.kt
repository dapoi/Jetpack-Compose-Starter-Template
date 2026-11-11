package util

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import util.ProjectConstants.CORE_COMMON
import util.ProjectConstants.KSP

object Dependencies {
    fun Project.composeDependencies() {
        dependencies {
            val bom = libs.androidx.compose.bom.get()
            implementation(platform(bom))
            androidTestImplementation(platform(bom))
            implementation(libs.androidx.compose.material3.get())
            implementation(libs.androidx.compose.ui.tooling.preview.get())
            debugImplementation(libs.androidx.compose.ui.tooling.debug.get())
            implementation(libs.androidx.activity.compose.get())
            implementation(libs.androidx.core.ktx.get())
            implementation(libs.coil.compose.get())
            implementation(libs.coil.network.get())
        }
    }

    fun Project.commonDependencies() {
        dependencies {
            api(libs.timber.get())
        }
    }

    fun Project.dataDependencies() {
        dependencies {
            implementation(project(CORE_COMMON))
            implementation(libs.dataStorePreferences.get())
            implementation(libs.okhttp.interceptor.get())
            implementation(libs.retrofit.lib.get())
            implementation(libs.retrofit.converter.get())
            implementation(libs.room.runtime.get())
            add(KSP, libs.room.compiler.get())
            debugImplementation(libs.chucker.debug.get())
            releaseImplementation(libs.chucker.release.get())
        }
    }

    fun Project.testDependencies() {
        dependencies {
            testImplementation(libs.junit4.get())
            testImplementation(libs.kotlinx.coroutines.test.get())
            testImplementation(libs.mockk.get())
            testImplementation(libs.turbine.get())
            testImplementation(libs.androidx.arch.core.testing.get())
            testImplementation(libs.androidx.junit.get())
            androidTestImplementation(libs.androidx.junit.get())
            androidTestImplementation(libs.androidx.espresso.core.get())
        }
    }

    fun Project.composeTestDependencies() {
        dependencies {
            androidTestImplementation(libs.androidx.compose.ui.test.junit4.get())
            debugImplementation(libs.androidx.compose.ui.test.manifest.get())
        }
    }
}