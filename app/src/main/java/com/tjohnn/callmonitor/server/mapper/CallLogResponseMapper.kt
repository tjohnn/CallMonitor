package com.tjohnn.callmonitor.server.mapper

import com.tjohnn.callmonitor.domain.call.model.CallLog
import com.tjohnn.callmonitor.server.model.CallLogResponseModel

private const val MILLISECONDS_IN_A_SECOND = 1000

class CallLogResponseMapper {
    fun map(input: CallLog): CallLogResponseModel {
        return CallLogResponseModel(
            phoneNumber = input.phoneNumber,
            callerName = input.callerName,
            startTime = input.startTime.toString(),
            durationInSeconds = (input.endTime.time - input.startTime.time) / MILLISECONDS_IN_A_SECOND,
            timesQueried = input.timesQueried
        )
    }
}
