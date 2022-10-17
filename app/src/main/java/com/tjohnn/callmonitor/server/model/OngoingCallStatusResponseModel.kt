package com.tjohnn.callmonitor.server.model

import kotlinx.serialization.Serializable

@Serializable
data class OngoingCallStatusResponseModel(
    val hasOngoingCall: Boolean,
    val phoneNumber: String?,
    val callerName: String?
)
