package com.project.compose.core.common.ui.component.attr

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import com.project.compose.core.common.R
import com.project.compose.core.common.ui.theme.AppTheme
import com.project.compose.core.common.ui.theme.Dimens
import com.project.compose.core.common.utils.onCustomClick

object AppTopBarAttr {

    data class ActionMenu(
        val icon: Int,
        val nameIcon: String,
        val showBadge: Boolean = false,
        val onClickActionMenu: (String) -> Unit
    )

    data class TopBarArgs(
        val actionMenus: List<ActionMenu> = emptyList(),
        val iconBack: Int? = null,
        val title: String? = null,
        val topBarColor: Color? = null,
        val titleColor: Color? = null,
        val iconBackColor: Color? = null,
        val actionMenusColor: Color? = null,
        val onClickBack: (() -> Unit)? = null
    )

    @Composable
    fun backIcon(
        icon: Int? = null,
        onClickBack: (() -> Unit)? = null
    ): @Composable () -> Unit = {
        Box(
            modifier = Modifier.Companion
                .clip(CircleShape)
                .onCustomClick { onClickBack?.invoke() },
            contentAlignment = Alignment.Companion.Center
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(icon ?: R.drawable.ic_back),
                contentDescription = "Back"
            )
        }
    }

    @Composable
    fun actionIcons(actionMenus: List<ActionMenu>): @Composable (RowScope.() -> Unit) = {
        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.Dp16)) {
            actionMenus.map { (resource, name, showBadge, onClickAction) ->
                Box(modifier = Modifier.Companion.size(Dimens.Dp32)) {
                    Icon(
                        modifier = Modifier.Companion
                            .size(Dimens.Dp24)
                            .onCustomClick { onClickAction(name) },
                        imageVector = ImageVector.Companion.vectorResource(resource),
                        contentDescription = "Action Button"
                    )
                    if (showBadge) Badge(
                        modifier = Modifier.Companion
                            .size(Dimens.Dp15)
                            .border(
                                width = Dimens.Dp2,
                                color = MaterialTheme.colorScheme.background,
                                shape = CircleShape
                            )
                            .align(Alignment.Companion.TopEnd)
                            .clip(CircleShape),
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }

    @Composable
    fun title(title: String): @Composable () -> Unit = {
        Text(
            text = title,
            style = AppTheme.typography.h3.copy(fontWeight = FontWeight.Companion.Medium)
        )
    }
}