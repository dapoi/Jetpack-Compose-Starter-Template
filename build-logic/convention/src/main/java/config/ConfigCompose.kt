package config

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import util.Dependencies.composeDependencies

internal fun Project.configCompose(extension: ApplicationExtension) {
    extension.buildFeatures { compose = true }
    composeDependencies()
}

internal fun Project.configCompose(extension: LibraryExtension) {
    extension.buildFeatures { compose = true }
    composeDependencies()
}