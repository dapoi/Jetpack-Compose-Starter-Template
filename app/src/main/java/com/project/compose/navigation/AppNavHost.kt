package com.project.compose.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.compose.core.navigation.base.BaseNavGraph
import com.project.compose.core.navigation.helper.navigateTo
import com.project.compose.core.navigation.route.HomeGraph.HomeLandingRoute
import com.project.compose.navigation.attr.AppNavHostAttr

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AppNavHost(navGraphs: Set<@JvmSuppressWildcards BaseNavGraph>) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    val bottomNavItems = remember { AppNavHostAttr.getBottomNav() }
    val showBottomNav = remember(currentDestination?.route) {
        currentDestination?.route?.split('.')?.last().orEmpty() in bottomNavItems.map {
            it.route.toString()
        }
    }

    Box(contentAlignment = BottomCenter) {
        NavHost(
            modifier = Modifier
                .background(colorScheme.background)
                .fillMaxSize()
                .systemBarsPadding(),
            navController = navController,
            startDestination = HomeLandingRoute::class
        ) {
            navGraphs.forEach { graph ->
                with(graph) { createGraph(navController) }
            }
        }

        /**
         *
         * Remove below code if you don't need bottom navigation
         *
         */
        AnimatedVisibility(visible = showBottomNav, enter = fadeIn(), exit = fadeOut()) {
            CompositionLocalProvider(LocalRippleConfiguration provides null) {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = colorScheme.surface
                ) {
                    bottomNavItems.map { item ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any {
                                it.hasRoute(item.route::class)
                            } == true,
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(item.icon),
                                    contentDescription = item.route::class.simpleName.orEmpty()
                                )
                            },
                            label = { Text(item.label) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = colorScheme.primary,
                                selectedTextColor = colorScheme.primary,
                                unselectedIconColor = Gray,
                                unselectedTextColor = Gray,
                                indicatorColor = Transparent
                            ),
                            onClick = {
                                navController.navigateTo(
                                    route = item.route,
                                    popUpTo = HomeLandingRoute::class,
                                    launchSingleTop = true
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}