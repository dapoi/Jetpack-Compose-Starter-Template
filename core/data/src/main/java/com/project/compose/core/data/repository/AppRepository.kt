package com.project.compose.core.data.repository

import com.project.compose.core.common.utils.state.ApiState
import com.project.compose.core.data.model.local.SampleModelEntity
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getListData(): Flow<ApiState<List<SampleModelEntity>>>
}