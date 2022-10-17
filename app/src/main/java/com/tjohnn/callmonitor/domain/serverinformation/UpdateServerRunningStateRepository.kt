package com.tjohnn.callmonitor.domain.serverinformation

interface UpdateServerRunningStateRepository {
    suspend fun updateServerRunningState(isServerRunning: Boolean)
}
