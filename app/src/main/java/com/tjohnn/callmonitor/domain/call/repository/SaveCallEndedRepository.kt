package com.tjohnn.callmonitor.domain.call.repository

import com.tjohnn.callmonitor.domain.call.model.SaveCallEndedRequest

interface SaveCallEndedRepository {
    suspend fun saveCallEnded(request: SaveCallEndedRequest)
}
