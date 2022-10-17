package com.tjohnn.callmonitor.data.call.mapper

import com.tjohnn.callmonitor.data.call.model.OngoingCallDataModel
import com.tjohnn.callmonitor.domain.call.model.OngoingCall

class OngoingCallDataMapper {
    fun map(input: OngoingCallDataModel): OngoingCall {
        return when (input) {
            OngoingCallDataModel.None -> OngoingCall.None
            is OngoingCallDataModel.Ongoing -> {
                OngoingCall.Ongoing(
                    phoneNumber = input.phoneNumber,
                    callerName = input.callerName,
                    startTime = input.startTime
                )
            }
        }
    }
}
