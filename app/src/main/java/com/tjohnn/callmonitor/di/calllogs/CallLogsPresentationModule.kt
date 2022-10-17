package com.tjohnn.callmonitor.di.calllogs

import com.tjohnn.callmonitor.domain.call.GetCallLogsUseCase
import com.tjohnn.callmonitor.presentation.calllogs.CallLogsIntentProcessor
import com.tjohnn.callmonitor.presentation.calllogs.CallLogsViewStateReducer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class CallLogsPresentationModule {

    @Provides
    fun providesCallLogsIntentProcessor(
        getCallLogsUseCase: GetCallLogsUseCase
    ) = CallLogsIntentProcessor(getCallLogsUseCase)

    @Provides
    fun providesCallLogsViewStateReducer() = CallLogsViewStateReducer()
}
