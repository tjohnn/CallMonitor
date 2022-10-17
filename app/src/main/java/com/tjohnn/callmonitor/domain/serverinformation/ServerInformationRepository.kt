package com.tjohnn.callmonitor.domain.serverinformation

import kotlinx.coroutines.flow.Flow

interface ServerInformationRepository {
    suspend fun getServerInformation(): Flow<ServerInformation>
}
