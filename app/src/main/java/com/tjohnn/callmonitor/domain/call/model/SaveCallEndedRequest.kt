package com.tjohnn.callmonitor.domain.call.model

import java.util.Date

data class SaveCallEndedRequest(
    val phoneNumber: String,
    val time: Date
)
