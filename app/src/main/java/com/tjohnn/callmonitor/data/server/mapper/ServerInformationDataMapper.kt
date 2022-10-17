package com.tjohnn.callmonitor.data.server.mapper

import com.tjohnn.callmonitor.data.server.model.ServerInformationDataModel
import com.tjohnn.callmonitor.domain.serverinformation.ServerInformation

class ServerInformationDataMapper {
    fun map(input: ServerInformationDataModel): ServerInformation {
        return ServerInformation(
            ipAddress = input.ipAddress,
            port = input.port,
            isRunning = input.isRunning,
            timeStarted = input.timeStarted
        )
    }
}
