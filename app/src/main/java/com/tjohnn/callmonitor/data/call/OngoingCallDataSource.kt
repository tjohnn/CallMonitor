package com.tjohnn.callmonitor.data.call

import com.tjohnn.callmonitor.data.cache.Cache
import com.tjohnn.callmonitor.data.call.mapper.OngoingCallRequestMapper
import com.tjohnn.callmonitor.data.call.model.OngoingCallDataModel
import com.tjohnn.callmonitor.domain.call.model.SaveCallStartedRequest
import kotlinx.coroutines.flow.Flow

class OngoingCallDataSource(
    private val ongoingCallCache: Cache<OngoingCallDataModel>,
    private val ongoingCallRequestMapper: OngoingCallRequestMapper
) {
    suspend fun get(): Flow<OngoingCallDataModel> {
        ongoingCallCache.emitIfEmpty(OngoingCallDataModel.None)
        return ongoingCallCache.flow
    }

    suspend fun saveOngoingCall(request: SaveCallStartedRequest) {
        val ongoingCall = ongoingCallRequestMapper.map(request)
        ongoingCallCache.emit(ongoingCall)
    }

    suspend fun reset() {
        ongoingCallCache.emit(OngoingCallDataModel.None)
    }
}
