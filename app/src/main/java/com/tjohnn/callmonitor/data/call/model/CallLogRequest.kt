package com.tjohnn.callmonitor.data.call.model

import com.tjohnn.callmonitor.domain.call.model.SaveCallEndedRequest

data class CallLogRequest(
    val ongoingCall: OngoingCallDataModel.Ongoing,
    val callEndRequest: SaveCallEndedRequest
)
