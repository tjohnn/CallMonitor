package com.tjohnn.callmonitor.di.server

import com.tjohnn.callmonitor.data.android.LocalIpAddressFacadeImpl
import com.tjohnn.callmonitor.data.server.LocalIpAddressFacade
import com.tjohnn.callmonitor.domain.call.GetOngoingCallStatusUseCase
import com.tjohnn.callmonitor.domain.call.QueryCallLogsUseCase
import com.tjohnn.callmonitor.domain.call.SaveCallEndedUseCase
import com.tjohnn.callmonitor.domain.call.SaveCallStartedUseCase
import com.tjohnn.callmonitor.domain.serverinformation.GetServerInformationUseCase
import com.tjohnn.callmonitor.domain.serverinformation.UpdateServerRunningStateUseCase
import com.tjohnn.callmonitor.server.ApiController
import com.tjohnn.callmonitor.server.CallLogsController
import com.tjohnn.callmonitor.server.mapper.CallLogResponseMapper
import com.tjohnn.callmonitor.server.mapper.OngoingCallResponseMapper
import com.tjohnn.callmonitor.time.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ServerModule {

    @Provides
    fun providesCallLogsController(
        updateServerRunningStateUseCase: UpdateServerRunningStateUseCase,
        saveCallEndedUseCase: SaveCallEndedUseCase,
        saveCallStartedUseCase: SaveCallStartedUseCase,
        timeProvider: TimeProvider
    ) = CallLogsController(
        updateServerRunningStateUseCase,
        saveCallEndedUseCase,
        saveCallStartedUseCase,
        timeProvider
    )

    @Provides
    fun providesApiController(
        getServerInformationUseCase: GetServerInformationUseCase,
        queryCallLogsUseCase: QueryCallLogsUseCase,
        getOngoingCallStatusUseCase: GetOngoingCallStatusUseCase,
        ongoingCallResponseMapper: OngoingCallResponseMapper,
        callLogResponseMapper: CallLogResponseMapper
    ) = ApiController(
        getServerInformationUseCase,
        queryCallLogsUseCase,
        getOngoingCallStatusUseCase,
        ongoingCallResponseMapper,
        callLogResponseMapper
    )

    @Provides
    fun providesLocalIpAddressFacade(): LocalIpAddressFacade = LocalIpAddressFacadeImpl()

    @Provides
    fun providesOngoingCallResponseMapper() = OngoingCallResponseMapper()

    @Provides
    fun providesCallLogResponseMapper() = CallLogResponseMapper()
}
