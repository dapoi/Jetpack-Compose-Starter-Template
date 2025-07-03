plugins {
    alias(libs.plugins.convention.application)
}

android {
    namespace = "com.project.compose"

    defaultConfig {
        applicationId = "com.project.compose"
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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