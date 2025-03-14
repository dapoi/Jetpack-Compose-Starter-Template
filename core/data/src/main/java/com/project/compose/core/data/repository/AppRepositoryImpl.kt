package com.project.compose.core.data.repository

import com.project.compose.core.data.source.remote.service.ApiService
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AppRepository