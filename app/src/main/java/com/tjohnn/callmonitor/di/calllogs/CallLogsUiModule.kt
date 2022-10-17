package com.tjohnn.callmonitor.di.calllogs

import com.tjohnn.callmonitor.ui.calllogs.CallLogsAdapter
import com.tjohnn.callmonitor.ui.calllogs.mapper.CallLogUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@InstallIn(FragmentComponent::class)
@Module
class CallLogsUiModule {

    @Provides
    fun providesCallLogUiMapper() = CallLogUiMapper()

    @Provides
    fun providesCallLogsAdapter() = CallLogsAdapter()
}
