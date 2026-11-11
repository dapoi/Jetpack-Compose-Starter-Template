package com.project.compose.core.navigation.helper

import androidx.compose.runtime.mutableStateOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.project.compose.core.navigation.route.HomeGraph.HomeLandingRoute
import com.project.compose.core.navigation.route.InfoGraph.InfoLandingRoute
import org.junit.Assert.assertEquals
import org.junit.Test

class NavigationStateTest {

    @Test
    fun `stacksInUse returns only startRoute when topLevelRoute is startRoute`() {
        val startRoute: NavKey = HomeLandingRoute
        val topLevelState = mutableStateOf<NavKey>(startRoute)
        val backStacks = mapOf<NavKey, NavBackStack<NavKey>>(
            HomeLandingRoute to NavBackStack(HomeLandingRoute),
            InfoLandingRoute to NavBackStack(InfoLandingRoute)
        )

        val navigationState = NavigationState(
            startRoute = startRoute,
            backStacks = backStacks,
            topLevelRoute = topLevelState
        )

        assertEquals(listOf(startRoute), navigationState.stacksInUse)
    }

    @Test
    fun `stacksInUse returns startRoute and current topLevelRoute when on secondary route`() {
        val startRoute: NavKey = HomeLandingRoute
        val topLevelState = mutableStateOf<NavKey>(InfoLandingRoute)
        val backStacks = mapOf<NavKey, NavBackStack<NavKey>>(
            HomeLandingRoute to NavBackStack(HomeLandingRoute),
            InfoLandingRoute to NavBackStack(InfoLandingRoute)
        )

        val navigationState = NavigationState(
            startRoute = startRoute,
            backStacks = backStacks,
            topLevelRoute = topLevelState
        )

        assertEquals(listOf(HomeLandingRoute, InfoLandingRoute), navigationState.stacksInUse)
    }

    @Test
    fun `updating topLevelRoute reflects in property`() {
        val startRoute: NavKey = HomeLandingRoute
        val topLevelState = mutableStateOf<NavKey>(startRoute)
        val backStacks = mapOf<NavKey, NavBackStack<NavKey>>(
            HomeLandingRoute to NavBackStack(HomeLandingRoute),
            InfoLandingRoute to NavBackStack(InfoLandingRoute)
        )

        val navigationState = NavigationState(
            startRoute = startRoute,
            backStacks = backStacks,
            topLevelRoute = topLevelState
        )

        navigationState.topLevelRoute = InfoLandingRoute
        assertEquals(InfoLandingRoute, navigationState.topLevelRoute)
        assertEquals(InfoLandingRoute, topLevelState.value)
    }
}
