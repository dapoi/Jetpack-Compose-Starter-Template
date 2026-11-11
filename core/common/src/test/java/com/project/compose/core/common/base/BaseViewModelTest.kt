package com.project.compose.core.common.base

import com.project.compose.core.common.util.MainDispatcherRule
import com.project.compose.core.common.utils.state.ApiState
import com.project.compose.core.common.utils.state.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BaseViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: BaseViewModel

    @Before
    fun setUp() {
        viewModel = BaseViewModel(mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `collectApiAsUiState emits Loading then Success and resets to Initial when resetState is true`() = runTest {
        val states = mutableListOf<UiState<String>>()
        val flow = flow {
            emit(ApiState.Loading)
            emit(ApiState.Success("Hello"))
        }

        viewModel.collectApiAsUiState(
            response = flow,
            resetState = true,
            updateState = { states.add(it) }
        )

        assertEquals(UiState.StateLoading, states[0])
        assertEquals(UiState.StateSuccess("Hello"), states[1])

        advanceTimeBy(350)
        assertEquals(UiState.StateInitial, states.last())
    }

    @Test
    fun `collectApiAsUiState keeps Success when resetState is false`() = runTest {
        val states = mutableListOf<UiState<String>>()
        val flow = flow {
            emit(ApiState.Loading)
            emit(ApiState.Success("Persist"))
        }

        viewModel.collectApiAsUiState(
            response = flow,
            resetState = false,
            updateState = { states.add(it) }
        )

        assertEquals(UiState.StateLoading, states[0])
        assertEquals(UiState.StateSuccess("Persist"), states[1])

        advanceTimeBy(350)
        assertEquals(2, states.size)
        assertEquals(UiState.StateSuccess("Persist"), states.last())
    }

    @Test
    fun `collectApiAsUiState emits Loading then Failed when error occurs`() = runTest {
        val states = mutableListOf<UiState<String>>()
        val throwable = RuntimeException("Server error")
        val flow = flow {
            emit(ApiState.Loading)
            emit(ApiState.Error<String>(throwable))
        }

        viewModel.collectApiAsUiState(
            response = flow,
            resetState = false,
            updateState = { states.add(it) }
        )

        assertEquals(UiState.StateLoading, states[0])
        assertEquals(UiState.StateFailed(throwable), states[1])
    }

    @Test
    fun `handleUiState triggers appropriate callback for each state`() {
        with(viewModel) {
            var successData: String? = null
            var loadingTriggered = false
            var failedError: Throwable? = null

            val successState: UiState<String> = UiState.StateSuccess("Success data")
            successState.handleUiState(
                onSuccess = { successData = it },
                onLoading = { loadingTriggered = true },
                onFailed = { failedError = it }
            )
            assertEquals("Success data", successData)
            assertFalse(loadingTriggered)

            loadingTriggered = false
            val loadingState: UiState<String> = UiState.StateLoading
            loadingState.handleUiState(
                onSuccess = { successData = it },
                onLoading = { loadingTriggered = true },
                onFailed = { failedError = it }
            )
            assertTrue(loadingTriggered)

            val error = RuntimeException("Crash")
            val failedState: UiState<String> = UiState.StateFailed(error)
            failedState.handleUiState(
                onSuccess = { },
                onLoading = { },
                onFailed = { failedError = it }
            )
            assertEquals(error, failedError)

            var initialTouched = false
            val initialState: UiState<String> = UiState.StateInitial
            initialState.handleUiState(
                onSuccess = { initialTouched = true },
                onLoading = { initialTouched = true },
                onFailed = { initialTouched = true }
            )
            assertFalse(initialTouched)
        }
    }
}
