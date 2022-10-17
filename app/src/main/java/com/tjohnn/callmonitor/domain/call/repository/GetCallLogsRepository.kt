package com.tjohnn.callmonitor.domain.call.repository

import com.tjohnn.callmonitor.domain.call.model.CallLog
import kotlinx.coroutines.flow.Flow

interface GetCallLogsRepository {
    suspend fun getCallLogs(): Flow<Collection<CallLog>>
}
