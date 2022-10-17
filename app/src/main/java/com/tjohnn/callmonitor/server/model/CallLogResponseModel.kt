package com.tjohnn.callmonitor.server.model

import kotlinx.serialization.Serializable

@Serializable
data class CallLogResponseModel(
    val startTime: String,
    val durationInSeconds: Long,
    val phoneNumber: String,
    val callerName: String,
    val timesQueried: Int
)
