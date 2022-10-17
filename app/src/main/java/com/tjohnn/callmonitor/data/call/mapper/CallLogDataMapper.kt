package com.tjohnn.callmonitor.data.call.mapper

import com.tjohnn.callmonitor.data.call.model.CallLogDataModel
import com.tjohnn.callmonitor.domain.call.model.CallLog

class CallLogDataMapper {
    fun map(input: CallLogDataModel): CallLog {
        return CallLog(
            phoneNumber = input.phoneNumber,
            callerName = input.callerName,
            startTime = input.startTime,
            endTime = input.endTime,
            timesQueried = input.timesQueried
        )
    }
}
