package com.project.compose.core.data.repository

import com.project.compose.core.common.base.BaseRepository
import com.project.compose.core.data.source.remote.ApiService
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AppRepository, BaseRepository() {
    override fun getListData() = collectApiResult(
        fetchApi = { apiService.getListData() },
        transformData = { it.map { response -> response.mapToEntity() } }
    )
}