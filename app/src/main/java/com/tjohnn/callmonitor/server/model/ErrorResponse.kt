package com.tjohnn.callmonitor.server.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String
)
