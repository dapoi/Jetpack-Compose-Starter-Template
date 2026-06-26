package com.project.compose.core.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.runtime.serialization.NavKeySerializer
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import com.project.compose.core.common.R
import com.project.compose.core.common.ui.component.AppBottomBar
import com.project.compose.core.common.ui.component.attr.AppBottomBarAttr.BottomNavItem
import com.project.compose.core.common.utils.LocalActivity
import com.project.compose.core.navigation.base.BaseNavGraph
import com.project.compose.core.navigation.helper.NavigationState
import com.project.compose.core.navigation.helper.Navigator
import com.project.compose.core.navigation.route.HomeGraph.HomeLandingRoute
import com.project.compose.core.navigation.route.InfoGraph.InfoLandingRoute

@Composable
fun AppNavHost(navGraphs: Set<@JvmSuppressWildcards BaseNavGraph>) {
    val activity = LocalActivity.current
    val bottomNavItems = remember { getBottomNav() }
    val topLevelRoutes = remember { bottomNavItems.map { it.route }.toSet() }
    val navigationState = rememberNavigationState(
        startRoute = HomeLandingRoute,
        topLevelRoutes = topLevelRoutes
    )
    val navigator = remember(activity) { Navigator(navigationState, activity) }
    val currentRoute = navigationState.backStacks[navigationState.topLevelRoute]?.lastOrNull()
    val showBottomNav = remember(currentRoute) {
        bottomNavItems.any { it.route == currentRoute }
    }
    val entryProvider = entryProvider {
        navGraphs.forEach { navGraph ->
            with(navGraph) { createGraph(navigator) }
        }
    }

    Box(contentAlignment = BottomCenter) {
        NavDisplay(
            modifier = Modifier.fillMaxSize(),
            entries = navigationState.toEntries(entryProvider),
            onBack = { navigator.goBack() }
        )

        AppBottomBar(
            modifier = Modifier.fillMaxWidth(),
            bottomNavItems = bottomNavItems,
            selectedRoute = navigationState.topLevelRoute,
            showBottomNav = showBottomNav,
            onClickNavItem = { route ->
                val navKey = route as NavKey
                if (navKey == navigationState.topLevelRoute) {
                    navigator.navigate(navKey, popUpTo = navKey, isInclusive = false)
                } else {
                    navigator.navigate(navKey)
                }
            }
        )
    }
}

@Composable
private fun rememberNavigationState(
    startRoute: NavKey,
    topLevelRoutes: Set<NavKey>
): NavigationState {
    val topLevelRoute = rememberSerializable(
        startRoute, topLevelRoutes,
        serializer = MutableStateSerializer(NavKeySerializer())
    ) { mutableStateOf(startRoute) }

    val backStacks = topLevelRoutes.associateWith { key -> rememberNavBackStack(key) }

    return remember(startRoute, topLevelRoutes) {
        NavigationState(
            startRoute = startRoute,
            topLevelRoute = topLevelRoute,
            backStacks = backStacks
        )
    }
}

@Composable
private fun NavigationState.toEntries(
    entryProvider: (NavKey) -> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>> {
    val decoratedEntries = backStacks.mapValues { (_, stack) ->
        val decorators = listOf<NavEntryDecorator<NavKey>>(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        )
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = decorators,
            entryProvider = entryProvider
        )
    }

    return stacksInUse
        .flatMap { decoratedEntries[it] ?: emptyList() }
        .toMutableStateList()
}

private fun getBottomNav() = listOf(
    BottomNavItem(route = HomeLandingRoute, icon = R.drawable.ic_home, label = "Home"),
    BottomNavItem(route = InfoLandingRoute, icon = R.drawable.ic_info, label = "Info")
)