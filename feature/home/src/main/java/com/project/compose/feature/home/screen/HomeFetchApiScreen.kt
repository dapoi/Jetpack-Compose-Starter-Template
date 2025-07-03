package com.project.compose.feature.home.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.project.compose.core.common.base.BaseScreen
import com.project.compose.core.common.ui.component.attr.AppTopBarAttr.TopBarArgs

@Composable
internal fun HomeFetchApiScreen(
    navController: NavController
) {
    BaseScreen(
        topBarArgs = TopBarArgs(
            title = "Fetch API Sample",
            onClickBack = { navController.popBackStack() }
        )
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(50) {
                Text(text = "Item #${it.plus(1)}")
            }
        }
    }
}