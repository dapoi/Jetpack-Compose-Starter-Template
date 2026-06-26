package com.project.compose.core.common.ui.component.attr

object AppBottomBarAttr {
    data class BottomNavItem<T : Any>(
        val route: T,
        val icon: Int,
        val label: String
    )
}