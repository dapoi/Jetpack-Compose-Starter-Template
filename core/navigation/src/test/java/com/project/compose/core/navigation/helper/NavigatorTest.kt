package com.project.compose.core.navigation.helper

import android.app.Activity
import androidx.compose.runtime.mutableStateOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import app.cash.turbine.test
import com.project.compose.core.navigation.route.HomeGraph.HomeDataTypeRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeFetchApiRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeLandingRoute
import com.project.compose.core.navigation.route.InfoGraph.InfoLandingRoute
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NavigatorTest {

    private lateinit var mockActivity: Activity
    private lateinit var homeBackStack: NavBackStack<NavKey>
    private lateinit var infoBackStack: NavBackStack<NavKey>
    private lateinit var navigationState: NavigationState
    private lateinit var navigator: Navigator

    @Before
    fun setUp() {
        mockActivity = mockk(relaxed = true)
        homeBackStack = NavBackStack(HomeLandingRoute)
        infoBackStack = NavBackStack(InfoLandingRoute)

        val backStacks = mapOf<NavKey, NavBackStack<NavKey>>(
            HomeLandingRoute to homeBackStack,
            InfoLandingRoute to infoBackStack
        )
        val topLevelState = mutableStateOf<NavKey>(HomeLandingRoute)

        navigationState = NavigationState(
            startRoute = HomeLandingRoute,
            backStacks = backStacks,
            topLevelRoute = topLevelState
        )

        navigator = Navigator(navigationState, mockActivity)
    }

    @Test
    fun `navigate adds route to current top level backstack`() {
        val targetRoute = HomeDataTypeRoute("test_param")
        navigator.navigate(targetRoute)

        assertEquals(2, homeBackStack.size)
        assertEquals(targetRoute, homeBackStack.last())
    }

    @Test
    fun `navigate prevents duplicate navigation if already on target route`() {
        val targetRoute = HomeDataTypeRoute("duplicate")
        navigator.navigate(targetRoute)
        assertEquals(2, homeBackStack.size)

        navigator.navigate(targetRoute)
        assertEquals(2, homeBackStack.size)
    }

    @Test
    fun `navigate switches top level route when target is top level`() {
        navigator.navigate(InfoLandingRoute)

        assertEquals(InfoLandingRoute, navigationState.topLevelRoute)
        assertEquals(1, infoBackStack.size)
    }

    @Test
    fun `navigate with isClearBackStacks clears all stacks and sets new route`() {
        navigator.navigate(HomeDataTypeRoute("temp1"))
        navigator.navigate(HomeFetchApiRoute)

        assertEquals(3, homeBackStack.size)

        navigator.navigate(HomeLandingRoute, isClearBackStacks = true)

        assertEquals(1, homeBackStack.size)
        assertEquals(HomeLandingRoute, homeBackStack.first())
        assertEquals(HomeLandingRoute, navigationState.topLevelRoute)
    }

    @Test
    fun `navigate with popUpTo trims backstack before navigating`() {
        val route1 = HomeDataTypeRoute("one")
        val route2 = HomeDataTypeRoute("two")
        navigator.navigate(route1)
        navigator.navigate(route2)

        assertEquals(3, homeBackStack.size)

        val route3 = HomeFetchApiRoute
        navigator.navigate(route3, popUpTo = HomeDataTypeRoute::class, isInclusive = true)

        assertTrue(homeBackStack.contains(route3))
        assertEquals(HomeFetchApiRoute, homeBackStack.last())
    }

    @Test
    fun `navigateBack pops current screen from backstack`() {
        val targetRoute = HomeDataTypeRoute("screen")
        navigator.navigate(targetRoute)
        assertEquals(2, homeBackStack.size)

        Thread.sleep(350)
        navigator.navigateBack()

        assertEquals(1, homeBackStack.size)
        assertEquals(HomeLandingRoute, homeBackStack.last())
    }

    @Test
    fun `navigateBack delivers result through navResult channel`() = runTest {
        navigator.navResult.test {
            val targetRoute = HomeDataTypeRoute("screen")
            navigator.navigate(targetRoute)

            Thread.sleep(350)
            navigator.navigateBack(result = "ResultData")

            val result = awaitItem()
            assertEquals("ResultData", result)
        }
    }

    @Test
    fun `navigateBack at root of secondary top level route switches to startRoute`() {
        navigator.navigate(InfoLandingRoute)
        assertEquals(InfoLandingRoute, navigationState.topLevelRoute)

        Thread.sleep(350)
        navigator.navigateBack()

        assertEquals(HomeLandingRoute, navigationState.topLevelRoute)
    }

    @Test
    fun `navigateBack at startRoute root calls activity finish`() {
        Thread.sleep(350)
        navigator.navigateBack()

        verify(exactly = 1) { mockActivity.finish() }
    }
}
