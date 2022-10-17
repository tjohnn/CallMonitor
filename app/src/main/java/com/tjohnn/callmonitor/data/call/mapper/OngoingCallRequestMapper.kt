package com.tjohnn.callmonitor.data.call.mapper

import com.tjohnn.callmonitor.data.call.model.OngoingCallDataModel
import com.tjohnn.callmonitor.domain.call.model.SaveCallStartedRequest

class OngoingCallRequestMapper {
    fun map(input: SaveCallStartedRequest): OngoingCallDataModel {
        return OngoingCallDataModel.Ongoing(
            phoneNumber = input.phoneNumber,
            callerName = input.callerName,
            startTime = input.time
        )
    }
}
