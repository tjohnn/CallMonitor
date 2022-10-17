package com.tjohnn.callmonitor.domain.call.model

import java.util.Date

data class CallLog(
    val startTime: Date,
    val endTime: Date,
    val phoneNumber: String,
    val callerName: String,
    val timesQueried: Int
)
