package com.project.compose.core.common.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.centerAlignedTopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import com.project.compose.core.common.ui.component.attr.AppTopBarAttr.TopBarArgs
import com.project.compose.core.common.ui.component.attr.AppTopBarAttr.actionIcons
import com.project.compose.core.common.ui.component.attr.AppTopBarAttr.backIcon
import com.project.compose.core.common.ui.component.attr.AppTopBarAttr.title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    topBarArgs: TopBarArgs,
    modifier: Modifier = Modifier,
    centerTopBar: Boolean = true,
) = with(topBarArgs) {
    if (centerTopBar) {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = title(title.orEmpty()),
            navigationIcon = backIcon(iconBack, onClickBack),
            actions = actionIcons(actionMenus),
            colors = centerAlignedTopAppBarColors(
                containerColor = topBarColor ?: colorScheme.background,
                navigationIconContentColor = iconBackColor ?: Unspecified,
                titleContentColor = titleColor ?: Unspecified,
                actionIconContentColor = actionMenusColor ?: Unspecified
            )
        )
    } else {
        TopAppBar(
            modifier = modifier,
            title = title(title.orEmpty()),
            navigationIcon = backIcon(iconBack, onClickBack),
            actions = actionIcons(actionMenus),
            colors = topAppBarColors(
                containerColor = topBarColor ?: colorScheme.background,
                navigationIconContentColor = iconBackColor ?: Unspecified,
                titleContentColor = titleColor ?: Unspecified,
                actionIconContentColor = actionMenusColor ?: Unspecified
            )
        )
    }
}