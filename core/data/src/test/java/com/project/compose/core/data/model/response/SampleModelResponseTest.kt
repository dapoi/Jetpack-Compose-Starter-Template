package com.project.compose.core.data.model.response

import org.junit.Assert.assertEquals
import org.junit.Test

class SampleModelResponseTest {

    @Test
    fun `mapToEntity maps valid fields correctly`() {
        val response = SampleModelResponse(id = 123, description = "Sample item")
        val entity = response.mapToEntity()

        assertEquals(123, entity.id)
        assertEquals("Sample item", entity.description)
    }

    @Test
    fun `mapToEntity falls back to default values when fields are null`() {
        val response = SampleModelResponse(id = null, description = null)
        val entity = response.mapToEntity()

        assertEquals(0, entity.id)
        assertEquals("", entity.description)
    }

    @Test
    fun `default constructor sets fields to null`() {
        val response = SampleModelResponse()
        assertEquals(null, response.id)
        assertEquals(null, response.description)
    }
}
