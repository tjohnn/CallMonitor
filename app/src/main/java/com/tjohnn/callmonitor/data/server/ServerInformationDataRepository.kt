package com.tjohnn.callmonitor.data.server

import com.tjohnn.callmonitor.data.server.mapper.ServerInformationDataMapper
import com.tjohnn.callmonitor.domain.serverinformation.ServerInformation
import com.tjohnn.callmonitor.domain.serverinformation.ServerInformationRepository
import com.tjohnn.callmonitor.domain.serverinformation.UpdateServerRunningStateRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ServerInformationDataRepository(
    private val serverInformationDataSource: ServerInformationDataSource,
    private val serverInformationDataMapper: ServerInformationDataMapper
) : ServerInformationRepository, UpdateServerRunningStateRepository {

    override suspend fun getServerInformation(): Flow<ServerInformation> {
        return serverInformationDataSource.get()
            .map(serverInformationDataMapper::map)
    }

    override suspend fun updateServerRunningState(isServerRunning: Boolean) {
        serverInformationDataSource.updateServerRunningState(isServerRunning)
    }
}
