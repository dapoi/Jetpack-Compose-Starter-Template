package com.project.compose.feature.home.screen

import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.compose.core.common.base.BaseScreen
import com.project.compose.core.navigation.helper.Navigator
import com.project.compose.core.navigation.route.HomeGraph.HomeFetchApiRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeReceivedObjectRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeReceivedObjectRoute.SampleObject

@Composable
internal fun HomeLandingScreen(navigator: Navigator) {
    BaseScreen(showDefaultTopBar = false) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Button(
                onClick = {
                    navigator.navigate(
                        HomeReceivedObjectRoute(
                            SampleObject(
                                name = "Daffa",
                                age = 25,
                                desc = "Hello, I'm Daffa. I am 25 years old and this is a sample object that I am passing to the next screen."
                            )
                        )
                    )
                }
            ) { Text(text = "Navigate with object args") }
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                onClick = { navigator.navigate(HomeFetchApiRoute) }
            ) { Text(text = "Fetch API") }
        }
    }
}