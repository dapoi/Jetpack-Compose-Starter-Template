package com.project.compose.core.common.utils.state

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UiStateTest {

    @Test
    fun `verify UiState instances and properties`() {
        val initial: UiState<String> = UiState.StateInitial
        val loading: UiState<String> = UiState.StateLoading
        val success: UiState<String> = UiState.StateSuccess("test_data")
        val error = RuntimeException("error")
        val failed: UiState<String> = UiState.StateFailed(error)

        assertTrue(initial is UiState.StateInitial)
        assertTrue(loading is UiState.StateLoading)
        assertTrue(success is UiState.StateSuccess)
        assertTrue(failed is UiState.StateFailed)
        assertEquals("test_data", (success as UiState.StateSuccess).data)
        assertEquals(error, (failed as UiState.StateFailed).throwable)
    }
}
