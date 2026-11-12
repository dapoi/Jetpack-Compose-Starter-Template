package com.project.compose.feature.info.viewmodel

import org.junit.Assert.assertNotNull
import org.junit.Test

class InfoViewModelTest {

    @Test
    fun `instantiate InfoViewModel`() {
        val viewModel = InfoViewModel()
        assertNotNull(viewModel)
    }
}
