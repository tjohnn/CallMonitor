package com.tjohnn.callmonitor.domain.call.repository

import com.tjohnn.callmonitor.domain.call.model.CallLog
import kotlinx.coroutines.flow.Flow

interface QueryCallLogsRepository {
    suspend fun queryCallLogs(): Flow<Collection<CallLog>>
}
