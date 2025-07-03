package com.project.compose.core.common.base

import com.project.compose.core.common.utils.state.ApiState.Error
import com.project.compose.core.common.utils.state.ApiState.Loading
import com.project.compose.core.common.utils.state.ApiState.Success
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

open class BaseRepository {
    /**
     * Collects API results and transforms them into a desired format.
     * Emits Loading state initially, then Success with transformed data or Error if an exception occurs.
     *
     * @param fetchApi A suspend function to fetch the API response.
     * @param transformData A function to transform the fetched data into the desired format.
     */
    inline fun <T, R> collectApiResult(
        crossinline fetchApi: suspend () -> T,
        crossinline transformData: (T) -> R
    ) = flow {
        emit(Loading)
        try {
            val response = fetchApi()
            val mappedData = transformData(response)
            emit(Success(mappedData))
        } catch (e: Throwable) {
            emit(Error(e))
        }
    }.catch { throwable ->
        emit(Error(throwable))
    }
}