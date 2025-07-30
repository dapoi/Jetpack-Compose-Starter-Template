plugins {
    alias(libs.plugins.convention.data)
}

android {
    room {
        schemaDirectory("$projectDir/schemas")
    }
}