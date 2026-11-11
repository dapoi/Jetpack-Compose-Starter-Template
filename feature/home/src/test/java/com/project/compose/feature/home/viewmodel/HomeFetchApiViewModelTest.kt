package com.project.compose.feature.home.viewmodel

import app.cash.turbine.test
import com.project.compose.core.common.utils.state.ApiState
import com.project.compose.core.common.utils.state.UiState
import com.project.compose.core.data.model.local.SampleModelEntity
import com.project.compose.core.data.repository.AppRepository
import com.project.compose.feature.home.util.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class HomeFetchApiViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: AppRepository = mockk()
    private lateinit var viewModel: HomeFetchApiViewModel

    @Before
    fun setUp() {
        viewModel = HomeFetchApiViewModel(
            repo = repository,
            ioDispatcher = mainDispatcherRule.testDispatcher
        )
    }

    @Test
    fun `initial state of sampleResponse is StateInitial`() {
        assertEquals(UiState.StateInitial, viewModel.sampleResponse.value)
    }

    @Test
    fun `getSampleData emits Loading then Success when repository succeeds`() = runTest {
        val sampleEntities = listOf(
            SampleModelEntity(id = 1, description = "Test 1"),
            SampleModelEntity(id = 2, description = "Test 2")
        )
        val responseFlow = flow {
            emit(ApiState.Loading)
            emit(ApiState.Success(sampleEntities))
        }

        every { repository.getListData() } returns responseFlow

        viewModel.getSampleData()

        assertEquals(UiState.StateSuccess(sampleEntities), viewModel.sampleResponse.value)

        // After reset delay (300ms), state resets to initial
        advanceTimeBy(350)
        assertEquals(UiState.StateInitial, viewModel.sampleResponse.value)

        verify(exactly = 1) { repository.getListData() }
    }

    @Test
    fun `getSampleData emits Loading then Failed when repository fails`() = runTest {
        val exception = IOException("Network error")
        val responseFlow = flow {
            emit(ApiState.Loading)
            emit(ApiState.Error<List<SampleModelEntity>>(exception))
        }

        every { repository.getListData() } returns responseFlow

        viewModel.getSampleData()

        assertEquals(UiState.StateFailed(exception), viewModel.sampleResponse.value)

        // After reset delay (300ms), state resets to initial
        advanceTimeBy(350)
        assertEquals(UiState.StateInitial, viewModel.sampleResponse.value)

        verify(exactly = 1) { repository.getListData() }
    }
}
