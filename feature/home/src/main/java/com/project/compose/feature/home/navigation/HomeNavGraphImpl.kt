package com.project.compose.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.project.compose.core.navigation.base.BaseNavGraph
import com.project.compose.core.navigation.helper.composableScreen
import com.project.compose.core.navigation.route.HomeGraph.HomeDetailCustomRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeDetailCustomRoute.Companion.customArgs
import com.project.compose.core.navigation.route.HomeGraph.HomeDetailRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeLandingRoute
import com.project.compose.feature.home.screen.HomeDetailCustomScreen
import com.project.compose.feature.home.screen.HomeDetailScreen
import com.project.compose.feature.home.screen.HomeLandingScreen
import javax.inject.Inject

class HomeNavGraphImpl @Inject constructor() : BaseNavGraph {
    override fun NavGraphBuilder.createGraph(navController: NavController) {
        composableScreen<HomeLandingRoute>(useDefaultTransition = false) { HomeLandingScreen(navController) }
        composableScreen<HomeDetailRoute> { HomeDetailScreen(navController) }
        composableScreen<HomeDetailCustomRoute>(customArgs = customArgs) { HomeDetailCustomScreen(navController) }
    }
}