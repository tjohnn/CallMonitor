package com.tjohnn.callmonitor.domain.serverinformation

import java.util.Date

data class ServerInformation(
    val ipAddress: String,
    val port: Int,
    val isRunning: Boolean,
    val timeStarted: Date?
)
