package com.tjohnn.callmonitor.di.home

import com.tjohnn.callmonitor.ui.home.mapper.ServerRunningStatePresentationToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class HomeUiModule {

    @Provides
    fun providesLocalIpAddressFacade() = ServerRunningStatePresentationToUiMapper()
}
