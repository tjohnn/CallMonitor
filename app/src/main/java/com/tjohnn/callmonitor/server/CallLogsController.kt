package com.tjohnn.callmonitor.server

import com.tjohnn.callmonitor.domain.call.SaveCallEndedUseCase
import com.tjohnn.callmonitor.domain.call.SaveCallStartedUseCase
import com.tjohnn.callmonitor.domain.call.model.SaveCallEndedRequest
import com.tjohnn.callmonitor.domain.call.model.SaveCallStartedRequest
import com.tjohnn.callmonitor.domain.serverinformation.UpdateServerRunningStateUseCase
import com.tjohnn.callmonitor.time.TimeProvider

class CallLogsController(
    private val updateServerRunningStateUseCase: UpdateServerRunningStateUseCase,
    private val saveCallEndedUseCase: SaveCallEndedUseCase,
    private val saveCallStartedUseCase: SaveCallStartedUseCase,
    private val timeProvider: TimeProvider
) {

    suspend fun setServiceStarted() {
        updateServerRunningStateUseCase.executeInBackground(true)
    }

    suspend fun setServiceStopped() {
        updateServerRunningStateUseCase.executeInBackground(false)
    }

    suspend fun registerCallStarted(phoneNumber: String, callerName: String) {
        val request = SaveCallStartedRequest(
            phoneNumber,
            callerName,
            timeProvider.getCurrentTime()
        )
        saveCallStartedUseCase.executeInBackground(request)
    }

    suspend fun registerCallEnded(phoneNumber: String) {
        val request = SaveCallEndedRequest(phoneNumber, timeProvider.getCurrentTime())
        saveCallEndedUseCase.executeInBackground(request)
    }
}
