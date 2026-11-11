package com.project.compose.core.common.base

import app.cash.turbine.test
import com.project.compose.core.common.utils.state.ApiState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class BaseRepositoryTest {

    private val repository = object : BaseRepository() {}

    @Test
    fun `collectApiResult emits Loading then Success with transformed data on successful call`() = runTest {
        val rawData = "raw_data"
        val expectedData = "TRANSFORMED: raw_data"

        repository.collectApiResult(
            fetchApi = { rawData },
            transformData = { "TRANSFORMED: $it" }
        ).test {
            assertEquals(ApiState.Loading, awaitItem())

            val successItem = awaitItem()
            assertTrue(successItem is ApiState.Success)
            assertEquals(expectedData, (successItem as ApiState.Success).data)

            awaitComplete()
        }
    }

    @Test
    fun `collectApiResult emits Loading then Error when fetchApi throws exception`() = runTest {
        val exception = IOException("Network connection failed")

        repository.collectApiResult(
            fetchApi = { throw exception },
            transformData = { it }
        ).test {
            assertEquals(ApiState.Loading, awaitItem())

            val errorItem = awaitItem()
            assertTrue(errorItem is ApiState.Error)
            assertEquals(exception, (errorItem as ApiState.Error).throwable)

            awaitComplete()
        }
    }

    @Test
    fun `collectApiResult emits Loading then Error when transformData throws exception`() = runTest {
        val exception = IllegalStateException("Transform error")

        repository.collectApiResult(
            fetchApi = { "some_data" },
            transformData = { throw exception }
        ).test {
            assertEquals(ApiState.Loading, awaitItem())

            val errorItem = awaitItem()
            assertTrue(errorItem is ApiState.Error)
            assertEquals(exception, (errorItem as ApiState.Error).throwable)

            awaitComplete()
        }
    }
}
