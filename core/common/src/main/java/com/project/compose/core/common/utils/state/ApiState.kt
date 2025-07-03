package com.project.compose.core.common.utils.state

sealed class ApiState<out T> {
    data class Success<out T>(val data: T?) : ApiState<T>()
    data class Error<out T>(val throwable: Throwable) : ApiState<T>()
    data object Loading : ApiState<Nothing>()
}