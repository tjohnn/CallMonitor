package com.tjohnn.callmonitor.data.call

import com.tjohnn.callmonitor.data.cache.Cache
import com.tjohnn.callmonitor.data.call.mapper.CallLogRequestMapper
import com.tjohnn.callmonitor.data.call.model.CallLogDataModel
import com.tjohnn.callmonitor.data.call.model.CallLogRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class CallLogsDataSource(
    private val callLogsCache: Cache<Collection<CallLogDataModel>>,
    private val callLogRequestMapper: CallLogRequestMapper
) {
    suspend fun get(): Flow<Collection<CallLogDataModel>> {
        callLogsCache.emitIfEmpty(emptyList())
        return callLogsCache.flow
    }

    suspend fun logCall(request: CallLogRequest) {
        val call = callLogRequestMapper.map(request)
        val newLogList = get().first().mapTo(mutableListOf()) { it }
            .apply { add(call) }
        callLogsCache.emit(newLogList)
    }

    suspend fun incrementQueryCounts() {
        val newLogList = get().first().mapTo(mutableListOf()) { it.copy(timesQueried = it.timesQueried + 1) }
        callLogsCache.emit(newLogList)
    }
}
