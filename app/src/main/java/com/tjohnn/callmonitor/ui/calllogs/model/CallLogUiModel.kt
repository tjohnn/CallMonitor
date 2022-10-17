package com.tjohnn.callmonitor.ui.calllogs.model

data class CallLogUiModel(
    val durationInSeconds: Long,
    val phoneNumber: String,
    val callerName: String
)
