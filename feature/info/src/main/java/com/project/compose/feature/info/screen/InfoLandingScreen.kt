package com.project.compose.feature.info.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.compose.core.common.base.BaseScreen
import androidx.compose.ui.text.style.TextAlign.Companion.Center as TextAlignCenter

@Composable
internal fun InfoLandingScreen() {
    BaseScreen(showDefaultTopBar = false) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            Text(
                text = "Thanks for using my template!, you can customize the app based on your needs. Don't forget to " +
                        "give a star on the GitHub repository.",
                textAlign = TextAlignCenter,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }
    }
}