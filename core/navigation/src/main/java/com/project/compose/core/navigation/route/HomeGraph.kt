package com.project.compose.core.navigation.route

import com.project.compose.core.navigation.helper.generateCustomNavType
import kotlinx.serialization.Serializable

@Serializable
sealed class HomeGraph {
    @Serializable
    data object HomeLandingRoute : HomeGraph()

    @Serializable
    data class HomeDetailRoute(val name: String) : HomeGraph()

    @Serializable
    data class HomeDetailCustomRoute(val data: CustomData) : HomeGraph() {
        @Serializable
        data class CustomData(val name: String) : HomeGraph()

        companion object {
            val customArgs = mapOf(generateCustomNavType<CustomData>())
        }
    }
}