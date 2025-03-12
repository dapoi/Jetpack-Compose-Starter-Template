package com.project.compose.feature.home.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.project.compose.core.navigation.route.HomeGraph.HomeDetailRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val args by lazy { savedStateHandle.toRoute<HomeDetailRoute>() }
}