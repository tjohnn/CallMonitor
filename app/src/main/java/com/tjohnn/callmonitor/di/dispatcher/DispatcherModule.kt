package com.tjohnn.callmonitor.di.dispatcher

import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import com.tjohnn.callmonitor.domain.dispatcher.DispatcherImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DispatcherModule {

    @Provides
    @Reusable
    fun providesDispatcher(): Dispatcher = DispatcherImpl()
}
