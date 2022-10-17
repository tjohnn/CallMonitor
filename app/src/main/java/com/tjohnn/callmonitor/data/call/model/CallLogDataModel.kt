package com.tjohnn.callmonitor.data.call.model

import java.util.Date

data class CallLogDataModel(
    val startTime: Date,
    val endTime: Date,
    val phoneNumber: String,
    val callerName: String,
    val timesQueried: Int
)
