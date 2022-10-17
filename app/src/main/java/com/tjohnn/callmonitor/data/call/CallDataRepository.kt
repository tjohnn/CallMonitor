package com.tjohnn.callmonitor.data.call

import com.tjohnn.callmonitor.data.call.mapper.CallLogDataMapper
import com.tjohnn.callmonitor.data.call.mapper.OngoingCallDataMapper
import com.tjohnn.callmonitor.data.call.model.CallLogRequest
import com.tjohnn.callmonitor.data.call.model.OngoingCallDataModel
import com.tjohnn.callmonitor.domain.call.model.CallLog
import com.tjohnn.callmonitor.domain.call.model.OngoingCall
import com.tjohnn.callmonitor.domain.call.model.SaveCallEndedRequest
import com.tjohnn.callmonitor.domain.call.model.SaveCallStartedRequest
import com.tjohnn.callmonitor.domain.call.repository.GetCallLogsRepository
import com.tjohnn.callmonitor.domain.call.repository.GetOngoingCallRepository
import com.tjohnn.callmonitor.domain.call.repository.QueryCallLogsRepository
import com.tjohnn.callmonitor.domain.call.repository.SaveCallEndedRepository
import com.tjohnn.callmonitor.domain.call.repository.SaveCallStartedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class CallDataRepository(
    private val ongoingCallDataSource: OngoingCallDataSource,
    private val callLogsDataSource: CallLogsDataSource,
    private val callLogDataMapper: CallLogDataMapper,
    private val ongoingCallDataMapper: OngoingCallDataMapper
) : SaveCallStartedRepository,
    SaveCallEndedRepository,
    GetOngoingCallRepository,
    GetCallLogsRepository,
    QueryCallLogsRepository {

    override suspend fun getCallLogs(): Flow<Collection<CallLog>> {
        return callLogsDataSource.get().map { it.map(callLogDataMapper::map) }
    }

    override suspend fun queryCallLogs(): Flow<Collection<CallLog>> {
        val callLogs = callLogsDataSource.get().first()
        callLogsDataSource.incrementQueryCounts()
        return flowOf(callLogs.map(callLogDataMapper::map))
    }

    override suspend fun getOngoingCall(): Flow<OngoingCall> {
        return ongoingCallDataSource.get().map(ongoingCallDataMapper::map)
    }

    override suspend fun saveCallEnded(request: SaveCallEndedRequest) {
        val ongoing = ongoingCallDataSource.get().first() as? OngoingCallDataModel.Ongoing ?: return
        if (request.phoneNumber != ongoing.phoneNumber) {
            return
        }
        ongoingCallDataSource.reset()
        val callRequest = CallLogRequest(ongoing, request)
        callLogsDataSource.logCall(callRequest)
    }

    override suspend fun saveCallStarted(request: SaveCallStartedRequest) {
        ongoingCallDataSource.saveOngoingCall(request)
    }
}
