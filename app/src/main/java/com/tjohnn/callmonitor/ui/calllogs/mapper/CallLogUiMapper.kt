package com.tjohnn.callmonitor.ui.calllogs.mapper

import com.tjohnn.callmonitor.domain.call.model.CallLog
import com.tjohnn.callmonitor.ui.calllogs.model.CallLogUiModel

private const val MILLISECONDS_IN_A_SECOND = 1000

class CallLogUiMapper {
    fun map(input: CallLog): CallLogUiModel {
        return CallLogUiModel(
            phoneNumber = input.phoneNumber,
            callerName = input.callerName,
            durationInSeconds = (input.endTime.time - input.startTime.time) / MILLISECONDS_IN_A_SECOND
        )
    }
}
