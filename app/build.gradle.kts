plugins {
    alias(libs.plugins.convention.application)
}

android {
    defaultConfig {
        applicationId = "com.project.compose"
        versionCode = 1
        versionName = "0.0.1"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.navigation)
    implementation(projects.feature.home)
    implementation(projects.feature.info)
}