package com.tjohnn.callmonitor.di.time

import com.tjohnn.callmonitor.time.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class TimeModule {

    @Provides
    @Reusable
    fun providesTimeProvider() = TimeProvider()
}
