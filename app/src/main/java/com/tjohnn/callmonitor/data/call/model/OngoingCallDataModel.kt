package com.tjohnn.callmonitor.data.call.model

import java.util.Date

sealed interface OngoingCallDataModel {
    object None : OngoingCallDataModel
    data class Ongoing(
        val phoneNumber: String,
        val callerName: String,
        val startTime: Date
    ) : OngoingCallDataModel
}
