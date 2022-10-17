package com.tjohnn.callmonitor.domain.call.model

import java.util.Date

data class SaveCallStartedRequest(
    val phoneNumber: String,
    val callerName: String,
    val time: Date
)
