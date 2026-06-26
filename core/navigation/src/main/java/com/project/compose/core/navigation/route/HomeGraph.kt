package com.project.compose.core.navigation.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface HomeGraph : NavKey {
    @Serializable
    data object HomeLandingRoute : HomeGraph

    @Serializable
    data class HomeDataTypeRoute(val name: String) : HomeGraph

    @Serializable
    data class HomeReceivedObjectRoute(val sampleObject: SampleObject) : HomeGraph {
        @Serializable
        data class SampleObject(
            val name: String,
            val age: Int,
            val desc: String
        )
    }

    @Serializable
    data object HomeFetchApiRoute : HomeGraph
}