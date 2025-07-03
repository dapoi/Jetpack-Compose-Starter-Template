package config

import com.android.build.api.dsl.CommonExtension
import utils.CollectionLibs.composeDependencies
import org.gradle.api.Project

internal fun Project.configCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) = commonExtension.apply {
    buildFeatures { compose = true }
    composeDependencies()
}