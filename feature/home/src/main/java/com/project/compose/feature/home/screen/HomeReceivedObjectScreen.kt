package com.project.compose.feature.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.compose.core.common.base.BaseScreen
import com.project.compose.core.common.ui.component.attr.AppTopBarAttr.TopBarArgs
import com.project.compose.core.navigation.helper.Navigator
import com.project.compose.core.navigation.route.HomeGraph.HomeReceivedObjectRoute

@Composable
internal fun HomeReceivedObjectScreen(
    navigator: Navigator,
    args: HomeReceivedObjectRoute
) = with(args) {
    BaseScreen(
        modifier = Modifier.fillMaxSize(),
        topBarArgs = TopBarArgs(
            title = "Received Data Class Args",
            onClickBack = { navigator.goBack() }
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                GetBiodata(sampleObject.name)
                GetBiodata(sampleObject.age.toString())
                GetBiodata(sampleObject.desc)
            }
        }
    }
}

@Composable
private fun GetBiodata(data: String) {
    Text(
        text = data,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(horizontal = 24.dp)
    )
}