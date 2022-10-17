package com.tjohnn.callmonitor.domain.call.model

import java.util.Date

sealed interface OngoingCall {
    object None : OngoingCall
    data class Ongoing(
        val phoneNumber: String,
        val callerName: String,
        val startTime: Date
    ) : OngoingCall
}
