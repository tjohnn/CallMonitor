package com.tjohnn.callmonitor.di.server

import com.tjohnn.callmonitor.data.cache.Cache
import com.tjohnn.callmonitor.data.server.LocalIpAddressFacade
import com.tjohnn.callmonitor.data.server.ServerInformationDataRepository
import com.tjohnn.callmonitor.data.server.ServerInformationDataSource
import com.tjohnn.callmonitor.data.server.mapper.ServerInformationDataMapper
import com.tjohnn.callmonitor.data.server.model.ServerInformationDataModel
import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import com.tjohnn.callmonitor.domain.serverinformation.GetServerInformationUseCase
import com.tjohnn.callmonitor.domain.serverinformation.ServerInformationRepository
import com.tjohnn.callmonitor.domain.serverinformation.UpdateServerRunningStateRepository
import com.tjohnn.callmonitor.domain.serverinformation.UpdateServerRunningStateUseCase
import com.tjohnn.callmonitor.time.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServerInformationModule {

    @Provides
    fun providesGetServerInformationUseCase(
        serverInformationRepository: ServerInformationRepository,
        dispatcher: Dispatcher
    ) = GetServerInformationUseCase(
        serverInformationRepository,
        dispatcher
    )

    @Provides
    fun providesUpdateServerRunningStateUseCase(
        updateServerRunningStateRepository: UpdateServerRunningStateRepository,
        dispatcher: Dispatcher
    ) = UpdateServerRunningStateUseCase(
        updateServerRunningStateRepository,
        dispatcher
    )

    @Provides
    fun providesServerInformationDataRepository(
        serverInformationDataSource: ServerInformationDataSource,
        serverInformationDataMapper: ServerInformationDataMapper
    ): ServerInformationDataRepository = ServerInformationDataRepository(
        serverInformationDataSource,
        serverInformationDataMapper
    )

    @Provides
    fun providesServerInformationRepository(
        serverInformationDataRepository: ServerInformationDataRepository
    ): ServerInformationRepository = serverInformationDataRepository

    @Provides
    fun providesUpdateServerRunningStateRepository(
        serverInformationDataRepository: ServerInformationDataRepository
    ): UpdateServerRunningStateRepository = serverInformationDataRepository

    @Provides
    fun providesServerInformationDataMapper() = ServerInformationDataMapper()

    @Provides
    fun providesServerInformationDataSource(
        serverInformationCache: Cache<ServerInformationDataModel>,
        localIpAddressFacade: LocalIpAddressFacade,
        timeProvider: TimeProvider
    ) = ServerInformationDataSource(
        serverInformationCache,
        localIpAddressFacade,
        timeProvider
    )

    @Provides
    @Singleton
    fun providesServerInformationCache() = Cache<ServerInformationDataModel>()
}
