package com.project.compose.core.data.source.remote

import com.project.compose.core.data.model.response.SampleModelResponse
import retrofit2.http.GET

interface ApiService {
    @GET("codingresources/codingResources")
    suspend fun getListData(): List<SampleModelResponse>
}