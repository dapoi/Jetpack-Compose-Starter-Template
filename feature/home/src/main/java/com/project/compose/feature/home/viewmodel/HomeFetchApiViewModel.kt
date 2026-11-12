package com.project.compose.feature.home.viewmodel

import com.project.compose.core.common.base.BaseViewModel
import com.project.compose.core.common.utils.state.UiState
import com.project.compose.core.common.utils.state.UiState.StateInitial
import com.project.compose.core.data.model.local.SampleModelEntity
import com.project.compose.core.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeFetchApiViewModel(
    private val repo: AppRepository,
    ioDispatcher: CoroutineDispatcher = IO
) : BaseViewModel(ioDispatcher) {

    @Inject
    constructor(repo: AppRepository) : this(repo, IO)

    private val _sampleResponse = MutableStateFlow<UiState<List<SampleModelEntity>>>(StateInitial)
    val sampleResponse = _sampleResponse.asStateFlow()

    fun getSampleData() = collectApiAsUiState(
        response = repo.getListData(),
        updateState = { _sampleResponse.value = it }
    )
}