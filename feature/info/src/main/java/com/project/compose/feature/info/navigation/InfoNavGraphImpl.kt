package com.project.compose.feature.info.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.project.compose.core.navigation.base.BaseNavGraph
import com.project.compose.core.navigation.helper.Navigator
import com.project.compose.core.navigation.route.InfoGraph.InfoLandingRoute
import com.project.compose.feature.info.screen.InfoLandingScreen
import javax.inject.Inject

class InfoNavGraphImpl @Inject constructor() : BaseNavGraph {
    override fun EntryProviderScope<NavKey>.createGraph(navigator: Navigator) {
        entry<InfoLandingRoute> { InfoLandingScreen() }
    }
}