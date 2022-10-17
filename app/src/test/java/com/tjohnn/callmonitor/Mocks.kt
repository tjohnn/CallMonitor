package com.tjohnn.callmonitor

import com.tjohnn.callmonitor.data.call.model.CallLogDataModel
import com.tjohnn.callmonitor.domain.call.model.CallLog
import com.tjohnn.callmonitor.domain.call.model.OngoingCall
import com.tjohnn.callmonitor.domain.serverinformation.ServerInformation
import com.tjohnn.callmonitor.presentation.home.HomeViewResult
import java.util.Date

val date1 = Date(2022, 8, 22, 12, 0, 0)
val date2 = Date(2022, 8, 22, 12, 0, 40)

val dataCallLog1 = CallLogDataModel(
    startTime = date1,
    endTime = date2,
    phoneNumber = "+49 1010101010",
    callerName = "John",
    timesQueried = 1
)
val dataCallLog2 = CallLogDataModel(
    startTime = date1,
    endTime = date2,
    phoneNumber = "+49 1111111111",
    callerName = "Doe",
    timesQueried = 2
)
val callLog1 = CallLog(
    startTime = date1,
    endTime = date2,
    phoneNumber = "+49 1010101010",
    callerName = "John",
    timesQueried = 1
)
val callLog2 = CallLog(
    startTime = date1,
    endTime = date2,
    phoneNumber = "+49 1111111111",
    callerName = "Doe",
    timesQueried = 2
)

val ongoingCall = OngoingCall.Ongoing(
    startTime = date1,
    phoneNumber = "+49 1010101010",
    callerName = "John"
)

val serverInformation = ServerInformation(
    ipAddress = "127.0.0.1",
    port = 8080,
    isRunning = true,
    timeStarted = date1
)

val serverInformationResult = HomeViewResult.ServerInformation(
    serverIpAddress = serverInformation.ipAddress,
    serverPort = serverInformation.port,
    isServerRunning = serverInformation.isRunning
)
