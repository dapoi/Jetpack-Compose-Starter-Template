package com.project.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.project.compose.core.common.ui.theme.StarterTheme
import com.project.compose.core.common.utils.LocalActivity
import com.project.compose.core.navigation.base.BaseNavGraph
import com.project.compose.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navGraphs: Set<@JvmSuppressWildcards BaseNavGraph>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StarterTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                   CompositionLocalProvider(LocalActivity provides this) {
                       AppNavHost(navGraphs = navGraphs)
                   }
                }
            }
        }
    }
}