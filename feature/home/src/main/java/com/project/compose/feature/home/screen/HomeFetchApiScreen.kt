package com.project.compose.feature.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.compose.core.common.base.BaseScreen
import com.project.compose.core.common.ui.component.attr.AppTopBarAttr.TopBarArgs
import com.project.compose.core.common.utils.state.collectAsStateValue
import com.project.compose.core.data.model.local.SampleModelEntity
import com.project.compose.feature.home.viewmodel.HomeFetchApiViewModel

@Composable
internal fun HomeFetchApiScreen(
    navController: NavController,
    viewModel: HomeFetchApiViewModel = hiltViewModel()
) = with(viewModel) {
    val sampleData = sampleResponse.collectAsStateValue()
    var showLoading by remember { mutableStateOf(false) }
    var contents by remember { mutableStateOf<List<SampleModelEntity>?>(null) }
    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(sampleData) {
        sampleData.handleUiState(
            onLoading = {
                showLoading = true
                showError = false
            },
            onSuccess = {
                showLoading = false
                contents = it
            },
            onFailed = {
                showLoading = false
                showError = true
            }
        )
    }

    LaunchedEffect(Unit) { getSampleData() }

    BaseScreen(
        topBarArgs = TopBarArgs(
            title = "Fetch API Sample",
            onClickBack = { navController.popBackStack() }
        )
    ) {
        contents?.let { data ->
            if (data.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        items = data,
                        key = { it.id }
                    ) {
                        Text(
                            text = "${it.id}. ${it.description}",
                            maxLines = 1,
                            overflow = Ellipsis
                        )
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                    Text(text = "No data available")
                }
            }
        }

        if (showLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                CircularProgressIndicator()
            }
        }

        if (showError) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                Text(text = "Failed to fetch data")
            }
        }
    }
}