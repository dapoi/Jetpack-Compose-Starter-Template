package com.project.compose.feature.info.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import com.project.compose.core.navigation.base.BaseNavGraph
import com.project.compose.core.navigation.helper.composableScreen
import com.project.compose.core.navigation.route.InfoGraph.InfoLandingRoute
import com.project.compose.feature.info.screen.InfoLandingScreen
import javax.inject.Inject

class InfoNavGraphImpl @Inject constructor() : BaseNavGraph {
    override fun NavGraphBuilder.createGraph(navController: NavController) {
        composableScreen<InfoLandingRoute>(
            enterTransition = fadeIn(),
            popExitTransition = fadeOut()
        ) { InfoLandingScreen() }
    }
}