package com.tjohnn.callmonitor.di.calllogs

import com.tjohnn.callmonitor.data.cache.Cache
import com.tjohnn.callmonitor.data.call.CallDataRepository
import com.tjohnn.callmonitor.data.call.CallLogsDataSource
import com.tjohnn.callmonitor.data.call.OngoingCallDataSource
import com.tjohnn.callmonitor.data.call.mapper.CallLogDataMapper
import com.tjohnn.callmonitor.data.call.mapper.CallLogRequestMapper
import com.tjohnn.callmonitor.data.call.mapper.OngoingCallDataMapper
import com.tjohnn.callmonitor.data.call.mapper.OngoingCallRequestMapper
import com.tjohnn.callmonitor.data.call.model.CallLogDataModel
import com.tjohnn.callmonitor.data.call.model.OngoingCallDataModel
import com.tjohnn.callmonitor.domain.call.GetCallLogsUseCase
import com.tjohnn.callmonitor.domain.call.GetOngoingCallStatusUseCase
import com.tjohnn.callmonitor.domain.call.QueryCallLogsUseCase
import com.tjohnn.callmonitor.domain.call.SaveCallEndedUseCase
import com.tjohnn.callmonitor.domain.call.SaveCallStartedUseCase
import com.tjohnn.callmonitor.domain.call.repository.GetCallLogsRepository
import com.tjohnn.callmonitor.domain.call.repository.GetOngoingCallRepository
import com.tjohnn.callmonitor.domain.call.repository.QueryCallLogsRepository
import com.tjohnn.callmonitor.domain.call.repository.SaveCallEndedRepository
import com.tjohnn.callmonitor.domain.call.repository.SaveCallStartedRepository
import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CallLogsDomainModule {

    @Provides
    fun providesGetCallLogsUseCase(
        getCallLogsRepository: GetCallLogsRepository,
        dispatcher: Dispatcher
    ) = GetCallLogsUseCase(getCallLogsRepository, dispatcher)

    @Provides
    fun providesGetOngoingCallStatusUseCase(
        getOngoingCallRepository: GetOngoingCallRepository,
        dispatcher: Dispatcher
    ) = GetOngoingCallStatusUseCase(getOngoingCallRepository, dispatcher)

    @Provides
    fun providesQueryCallLogsUseCase(
        queryCallLogsRepository: QueryCallLogsRepository,
        dispatcher: Dispatcher
    ) = QueryCallLogsUseCase(queryCallLogsRepository, dispatcher)

    @Provides
    fun providesSaveCallStartedUseCase(
        saveCallStartedRepository: SaveCallStartedRepository,
        dispatcher: Dispatcher
    ) = SaveCallStartedUseCase(saveCallStartedRepository, dispatcher)

    @Provides
    fun providesSaveCallEndedUseCase(
        saveCallEndedRepository: SaveCallEndedRepository,
        dispatcher: Dispatcher
    ) = SaveCallEndedUseCase(saveCallEndedRepository, dispatcher)

    @Provides
    fun providesCallDataRepository(
        ongoingCallDataSource: OngoingCallDataSource,
        callLogsDataSource: CallLogsDataSource,
        callLogDataMapper: CallLogDataMapper,
        ongoingCallDataMapper: OngoingCallDataMapper
    ) = CallDataRepository(
        ongoingCallDataSource,
        callLogsDataSource,
        callLogDataMapper,
        ongoingCallDataMapper
    )

    @Provides
    fun providesSaveCallStartedRepository(
        callDataRepository: CallDataRepository
    ): SaveCallStartedRepository = callDataRepository

    @Provides
    fun providesSaveCallEndedRepository(
        callDataRepository: CallDataRepository
    ): SaveCallEndedRepository = callDataRepository

    @Provides
    fun providesGetOngoingCallRepository(
        callDataRepository: CallDataRepository
    ): GetOngoingCallRepository = callDataRepository

    @Provides
    fun providesGetCallLogsRepository(
        callDataRepository: CallDataRepository
    ): GetCallLogsRepository = callDataRepository

    @Provides
    fun providesQueryCallLogsRepository(
        callDataRepository: CallDataRepository
    ): QueryCallLogsRepository = callDataRepository

    @Provides
    fun providesOngoingCallDataSource(
        ongoingCallCache: Cache<OngoingCallDataModel>,
        ongoingCallRequestMapper: OngoingCallRequestMapper
    ) = OngoingCallDataSource(
        ongoingCallCache,
        ongoingCallRequestMapper
    )

    @Provides
    fun providesCallLogsDataSource(
        callLogsCache: Cache<Collection<CallLogDataModel>>,
        callLogRequestMapper: CallLogRequestMapper
    ) = CallLogsDataSource(
        callLogsCache,
        callLogRequestMapper
    )

    @Provides
    fun providesCallLogDataMapper() = CallLogDataMapper()

    @Provides
    fun providesOngoingCallDataMapper() = OngoingCallDataMapper()

    @Provides
    fun providesOngoingCallRequestMapper() = OngoingCallRequestMapper()

    @Provides
    fun providesCallLogRequestMapper() = CallLogRequestMapper()

    @Provides
    @Singleton
    fun providesCallLogsCache() = Cache<Collection<CallLogDataModel>>()

    @Provides
    @Singleton
    fun providesOngoingCallCache() = Cache<OngoingCallDataModel>()
}
