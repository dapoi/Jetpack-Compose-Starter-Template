package com.project.compose.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.project.compose.core.navigation.base.BaseNavGraph
import com.project.compose.core.navigation.helper.composableScreen
import com.project.compose.core.navigation.route.HomeGraph.HomeDataClassRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeDataTypeRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeFetchApiRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeLandingRoute
import com.project.compose.feature.home.screen.HomeDataClassScreen
import com.project.compose.feature.home.screen.HomeDataTypeScreen
import com.project.compose.feature.home.screen.HomeFetchApiScreen
import com.project.compose.feature.home.screen.HomeLandingScreen
import javax.inject.Inject

class HomeNavGraphImpl @Inject constructor() : BaseNavGraph {
    override fun NavGraphBuilder.createGraph(navController: NavController) {
        composableScreen<HomeLandingRoute> {
            HomeLandingScreen(navController)
        }
        composableScreen<HomeDataTypeRoute> {
            HomeDataTypeScreen(navController)
        }
        composableScreen<HomeDataClassRoute>(
            customArgs = HomeDataClassRoute.typeMap
        ) {
            HomeDataClassScreen(navController)
        }
        composableScreen<HomeFetchApiRoute> {
            HomeFetchApiScreen(navController)
        }
    }
}