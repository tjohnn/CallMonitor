package com.tjohnn.callmonitor.data.server

import com.tjohnn.callmonitor.data.cache.Cache
import com.tjohnn.callmonitor.data.server.model.ServerInformationDataModel
import com.tjohnn.callmonitor.domain.exception.LocalIpAddressException
import com.tjohnn.callmonitor.server.SERVER_PORT
import com.tjohnn.callmonitor.time.TimeProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ServerInformationDataSource(
    private val serverInformationCache: Cache<ServerInformationDataModel>,
    private val localIpAddressFacade: LocalIpAddressFacade,
    private val timeProvider: TimeProvider
) {
    suspend fun get(): Flow<ServerInformationDataModel> {
        serverInformationCache.saveIfEmpty(
            ServerInformationDataModel(
                ipAddress = localIpAddressFacade.getSystemLocalIpAddress() ?: throw LocalIpAddressException(),
                port = SERVER_PORT,
                isRunning = false,
                timeStarted = null
            )
        )
        return serverInformationCache.flow
    }

    suspend fun updateServerRunningState(isServerRunning: Boolean) {
        val timeStarted = if (isServerRunning) {
            timeProvider.getCurrentTime()
        } else {
            null
        }
        val newServerInfo = get().first().copy(
            isRunning = isServerRunning,
            timeStarted = timeStarted
        )
        serverInformationCache.save(newServerInfo)
    }
}
