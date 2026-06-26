package com.project.compose.feature.home.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.project.compose.core.navigation.base.BaseNavGraph
import com.project.compose.core.navigation.helper.Navigator
import com.project.compose.core.navigation.route.HomeGraph.HomeFetchApiRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeLandingRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeReceivedObjectRoute
import com.project.compose.feature.home.screen.HomeFetchApiScreen
import com.project.compose.feature.home.screen.HomeLandingScreen
import com.project.compose.feature.home.screen.HomeReceivedObjectScreen
import javax.inject.Inject

class HomeNavGraphImpl @Inject constructor() : BaseNavGraph {
    override fun EntryProviderScope<NavKey>.createGraph(navigator: Navigator) {
        entry<HomeLandingRoute> { HomeLandingScreen(navigator) }
        entry<HomeReceivedObjectRoute> { args -> HomeReceivedObjectScreen(navigator, args) }
        entry<HomeFetchApiRoute> { HomeFetchApiScreen(navigator) }
    }
}