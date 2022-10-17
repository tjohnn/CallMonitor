package com.tjohnn.callmonitor.domain.call.repository

import com.tjohnn.callmonitor.domain.call.model.SaveCallStartedRequest

interface SaveCallStartedRepository {
    suspend fun saveCallStarted(request: SaveCallStartedRequest)
}
