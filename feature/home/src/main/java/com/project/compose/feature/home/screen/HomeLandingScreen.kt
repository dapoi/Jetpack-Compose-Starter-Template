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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.compose.core.common.base.BaseScreen
import com.project.compose.core.navigation.helper.navigateTo
import com.project.compose.core.navigation.route.HomeGraph.HomeDataClassRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeDataClassRoute.CustomData
import com.project.compose.core.navigation.route.HomeGraph.HomeDataTypeRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeFetchApiRoute
import com.project.compose.feature.home.viewmodel.HomeLandingViewModel

@Composable
internal fun HomeLandingScreen(
    navController: NavController,
    viewModel: HomeLandingViewModel = hiltViewModel()
) = with(viewModel) {
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
                    navController.navigateTo(
                        HomeDataTypeRoute("This is primitive type data")
                    )
                }
            ) { Text(text = "Navigate with data type args") }
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                onClick = {
                    navController.navigateTo(
                        HomeDataClassRoute(
                            CustomData(
                                name = "Daffa",
                                age = 24,
                                desc = "Hello, I'm Daffa. I am 24 years old and this is a custom data class."
                            )
                        )
                    )
                }
            ) { Text(text = "Navigate with data class args") }
            Spacer(modifier = Modifier.size(24.dp))
            Button(
                onClick = { navController.navigateTo(HomeFetchApiRoute) }
            ) { Text(text = "Fetch API") }
        }
    }
}