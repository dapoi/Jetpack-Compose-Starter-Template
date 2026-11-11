package com.project.compose.core.common.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class DebounceHandlerTest {

    @Test
    fun `first click event is invoked`() {
        val handler = DebounceHandler(interval = 500L)
        var callCount = 0

        handler.processClick { callCount++ }

        assertEquals(1, callCount)
    }

    @Test
    fun `rapid successive clicks within interval are ignored`() {
        val handler = DebounceHandler(interval = 500L)
        var callCount = 0

        handler.processClick { callCount++ }
        handler.processClick { callCount++ }
        handler.processClick { callCount++ }

        assertEquals(1, callCount)
    }

    @Test
    fun `click after interval is invoked`() {
        val handler = DebounceHandler(interval = 50L)
        var callCount = 0

        handler.processClick { callCount++ }
        Thread.sleep(60L)
        handler.processClick { callCount++ }

        assertEquals(2, callCount)
    }

    @Test
    fun `null click event does not crash`() {
        val handler = DebounceHandler(interval = 500L)
        handler.processClick(null)
    }
}
