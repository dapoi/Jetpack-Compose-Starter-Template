package com.project.compose.core.data.repository

import app.cash.turbine.test
import com.project.compose.core.common.utils.state.ApiState
import com.project.compose.core.data.model.local.SampleModelEntity
import com.project.compose.core.data.model.response.SampleModelResponse
import com.project.compose.core.data.source.remote.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class AppRepositoryImplTest {

    private val apiService: ApiService = mockk()
    private lateinit var repository: AppRepositoryImpl

    @Before
    fun setUp() {
        repository = AppRepositoryImpl(apiService)
    }

    @Test
    fun `getListData emits Loading then Success with mapped entities when API succeeds`() = runTest {
        val remoteList = listOf(
            SampleModelResponse(id = 1, description = "Item 1"),
            SampleModelResponse(id = 2, description = "Item 2")
        )
        val expectedEntities = listOf(
            SampleModelEntity(id = 1, description = "Item 1"),
            SampleModelEntity(id = 2, description = "Item 2")
        )

        coEvery { apiService.getListData() } returns remoteList

        repository.getListData().test {
            assertEquals(ApiState.Loading, awaitItem())

            val successItem = awaitItem()
            assertTrue(successItem is ApiState.Success)
            assertEquals(expectedEntities, (successItem as ApiState.Success).data)

            awaitComplete()
        }

        coVerify(exactly = 1) { apiService.getListData() }
    }

    @Test
    fun `getListData emits Loading then Error when API throws exception`() = runTest {
        val exception = IOException("Failed to connect")

        coEvery { apiService.getListData() } throws exception

        repository.getListData().test {
            assertEquals(ApiState.Loading, awaitItem())

            val errorItem = awaitItem()
            assertTrue(errorItem is ApiState.Error)
            assertEquals(exception, (errorItem as ApiState.Error).throwable)

            awaitComplete()
        }

        coVerify(exactly = 1) { apiService.getListData() }
    }
}
