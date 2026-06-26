package com.project.compose.core.common.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.project.compose.core.common.ui.component.attr.AppBottomBarAttr
import com.project.compose.core.common.ui.component.attr.AppBottomBarAttr.BottomNavItem


@Composable
fun AppBottomBar(
    bottomNavItems:  List<BottomNavItem<out Any>>,
    showBottomNav: Boolean,
    selectedRoute: Any?,
    modifier: Modifier = Modifier,
    onClickNavItem: (Any) -> Unit
) {
    AnimatedVisibility(visible = showBottomNav, enter = fadeIn(), exit = fadeOut()) {
        CompositionLocalProvider(LocalRippleConfiguration provides null) {
            BottomAppBar(
                modifier = modifier,
                containerColor = colorScheme.surface
            ) {
                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        selected = item.route == selectedRoute,
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
                        onClick = { onClickNavItem(item.route) }
                    )
                }
            }
        }
    }
}