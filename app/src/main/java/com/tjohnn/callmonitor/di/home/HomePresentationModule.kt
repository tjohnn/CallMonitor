package com.tjohnn.callmonitor.di.home

import com.tjohnn.callmonitor.domain.serverinformation.GetServerInformationUseCase
import com.tjohnn.callmonitor.presentation.home.HomeIntentProcessor
import com.tjohnn.callmonitor.presentation.home.HomeViewStateReducer
import com.tjohnn.callmonitor.presentation.home.mapper.ServerInformationToHomeResultMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class HomePresentationModule {

    @Provides
    fun providesHomeIntentProcessor(
        getServerInformationUseCase: GetServerInformationUseCase,
        serverInformationToHomeResultMapper: ServerInformationToHomeResultMapper
    ) = HomeIntentProcessor(
        getServerInformationUseCase,
        serverInformationToHomeResultMapper
    )

    @Provides
    fun providesHomeViewStateReducer() = HomeViewStateReducer()

    @Provides
    fun providesServerInformationToHomeResultMapper() = ServerInformationToHomeResultMapper()
}
