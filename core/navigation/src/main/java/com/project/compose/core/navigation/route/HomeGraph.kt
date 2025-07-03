package com.project.compose.core.navigation.route

import com.project.compose.core.navigation.helper.generateCustomNavType
import kotlinx.serialization.Serializable

@Serializable
sealed class HomeGraph {
    @Serializable
    data object HomeLandingRoute : HomeGraph()

    @Serializable
    data class HomeDataTypeRoute(val name: String) : HomeGraph()

    @Serializable
    data class HomeDataClassRoute(val data: CustomData) : HomeGraph() {
        @Serializable
        data class CustomData(
            val name: String,
            val age: Int,
            val desc: String
        ) : HomeGraph()

        companion object {
            val typeMap = mapOf(generateCustomNavType<CustomData>())
        }
    }

    @Serializable
    data object HomeFetchApiRoute : HomeGraph()
}