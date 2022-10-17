package com.tjohnn.callmonitor.data.server.model

import java.util.Date

data class ServerInformationDataModel(
    val ipAddress: String,
    val port: Int,
    val isRunning: Boolean,
    val timeStarted: Date?
)
