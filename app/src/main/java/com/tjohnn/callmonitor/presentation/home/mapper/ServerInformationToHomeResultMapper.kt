package com.tjohnn.callmonitor.presentation.home.mapper

import com.tjohnn.callmonitor.domain.serverinformation.ServerInformation
import com.tjohnn.callmonitor.presentation.home.HomeViewResult

class ServerInformationToHomeResultMapper {
    fun toResult(input: ServerInformation): HomeViewResult.ServerInformation {
        return HomeViewResult.ServerInformation(
            serverIpAddress = input.ipAddress,
            serverPort = input.port,
            isServerRunning = input.isRunning
        )
    }
}
