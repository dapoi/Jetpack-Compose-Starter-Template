package com.project.compose.feature.info.di

import com.project.compose.core.navigation.base.BaseNavGraph
import com.project.compose.feature.info.navigation.InfoNavGraphImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class InfoNavModule {

    @Binds
    @IntoSet
    abstract fun bindInfoNavGraph(navGraph: InfoNavGraphImpl): BaseNavGraph
}