package com.project.compose.core.common.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.compose.core.common.ui.component.AppTopBar
import com.project.compose.core.common.ui.component.attr.AppTopBarAttr.TopBarArgs
import com.project.compose.core.common.ui.theme.Dimens.Dp24
import com.project.compose.core.common.utils.LocalActivity

/**
 * BaseScreen is a composable function that provides a standard layout for screens in the application.
 * It includes a top bar, content area, and optional configurations for screen orientation,
 * system bar padding, and top bar alignment.
 * @param modifier Modifier to be applied to the screen layout.
 * @param topBarArgs Arguments for configuring the top bar, such as title, actions, and colors.
 * @param centerTopBar If true, the top bar will be centered; otherwise, it will be aligned to the start.
 * @param clipToTopBar If true, the content will overlap with the top bar; otherwise, it will be below the top bar.
 * @param lockOrientation If true, the screen orientation will be locked to portrait mode.
 * @param showDefaultTopBar If true, the default top bar will be shown; otherwise, it will be hidden.
 * @param systemBarsPadding If true, the content will be padded to avoid system bars (like status and navigation bars).
 * @param content The content to be displayed in the screen's body.
 * @return A composable function that represents the base screen layout.
 */
@SuppressLint("SourceLockedOrientationActivity")
@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    topBarArgs: TopBarArgs = TopBarArgs(),
    centerTopBar: Boolean = false,
    clipToTopBar: Boolean = false,
    lockOrientation: Boolean = true,
    showDefaultTopBar: Boolean = true,
    systemBarsPadding: Boolean = true,
    content: @Composable () -> Unit
) = with(topBarArgs) {
    val activity = LocalActivity.current
    if (lockOrientation) activity.requestedOrientation = SCREEN_ORIENTATION_PORTRAIT

    BaseScreenContent(
        modifier = modifier,
        topBarArgs = this@with,
        centerTopBar = centerTopBar,
        clipToTopBar = clipToTopBar,
        showDefaultTopBar = showDefaultTopBar,
        systemBarsPadding = systemBarsPadding,
        content = content
    )
}

@Composable
private fun BaseScreenContent(
    topBarArgs: TopBarArgs,
    centerTopBar: Boolean,
    clipToTopBar: Boolean,
    showDefaultTopBar: Boolean,
    systemBarsPadding: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = if (clipToTopBar) {
    Box(
        modifier = modifier
            .background(color = colorScheme.background)
            .then(if (systemBarsPadding) Modifier.systemBarsPadding() else Modifier)
    ) {
        content()
        if (showDefaultTopBar) AppTopBar(
            topBarArgs = topBarArgs,
            centerTopBar = centerTopBar,
            modifier = Modifier.padding(PaddingValues(horizontal = Dp24))
        )
    }
} else {
    Column(
        modifier = modifier
            .background(color = colorScheme.background)
            .padding(horizontal = Dp24)
            .then(if (systemBarsPadding) Modifier.systemBarsPadding() else Modifier)
    ) {
        if (showDefaultTopBar) AppTopBar(
            topBarArgs = topBarArgs,
            centerTopBar = centerTopBar
        )
        content()
    }
}