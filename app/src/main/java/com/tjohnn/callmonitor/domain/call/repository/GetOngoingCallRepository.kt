package com.tjohnn.callmonitor.domain.call.repository

import com.tjohnn.callmonitor.domain.call.model.OngoingCall
import kotlinx.coroutines.flow.Flow

interface GetOngoingCallRepository {
    suspend fun getOngoingCall(): Flow<OngoingCall>
}
