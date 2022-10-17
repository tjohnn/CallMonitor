package com.tjohnn.callmonitor.data.call.mapper

import com.tjohnn.callmonitor.data.call.model.CallLogDataModel
import com.tjohnn.callmonitor.data.call.model.CallLogRequest

class CallLogRequestMapper {
    fun map(input: CallLogRequest): CallLogDataModel {
        return CallLogDataModel(
            phoneNumber = input.ongoingCall.phoneNumber,
            callerName = input.ongoingCall.callerName,
            startTime = input.ongoingCall.startTime,
            endTime = input.callEndRequest.time,
            timesQueried = 0
        )
    }
}
