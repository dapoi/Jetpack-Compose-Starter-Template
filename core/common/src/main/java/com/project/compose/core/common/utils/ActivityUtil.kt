package com.project.compose.core.common.utils

import androidx.activity.ComponentActivity
import androidx.compose.runtime.staticCompositionLocalOf

val LocalActivity = staticCompositionLocalOf<ComponentActivity> { noLocalProvided() }

private fun noLocalProvided(): Nothing {
    error("CompositionLocal LocalActivity not present")
}