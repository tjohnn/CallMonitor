package com.tjohnn.callmonitor.server.mapper

import com.tjohnn.callmonitor.domain.call.model.OngoingCall
import com.tjohnn.callmonitor.server.model.OngoingCallStatusResponseModel

class OngoingCallResponseMapper {

    fun map(input: OngoingCall): OngoingCallStatusResponseModel {
        return when (input) {
            OngoingCall.None -> {
                OngoingCallStatusResponseModel(
                    hasOngoingCall = false,
                    phoneNumber = null,
                    callerName = null
                )
            }
            is OngoingCall.Ongoing -> {
                OngoingCallStatusResponseModel(
                    hasOngoingCall = true,
                    phoneNumber = input.phoneNumber,
                    callerName = input.callerName
                )
            }
        }
    }
}
