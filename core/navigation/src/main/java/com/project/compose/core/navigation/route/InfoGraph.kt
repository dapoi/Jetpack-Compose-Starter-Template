package com.project.compose.core.navigation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface InfoGraph : NavKey {
    @Serializable
    data object InfoLandingRoute : InfoGraph
}