package com.project.compose.navigation.attr

import com.project.compose.core.common.R
import com.project.compose.core.navigation.route.HomeGraph.HomeLandingRoute
import com.project.compose.core.navigation.route.InfoGraph.InfoLandingRoute

object AppNavHostAttr {
    data class BottomNavItem<T : Any>(
        val route: T,
        val icon: Int,
        val label: String
    )

    fun getBottomNav() = listOf(
        BottomNavItem(route = HomeLandingRoute, icon = R.drawable.ic_home, label = "Home"),
        BottomNavItem(route = InfoLandingRoute, icon = R.drawable.ic_info, label = "Info")
    )
}