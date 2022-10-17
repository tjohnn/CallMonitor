package com.tjohnn.callmonitor.server.model

import kotlinx.serialization.Serializable

@Serializable
data class ServerInformationResponseModel(
    val startTime: String,
    val services: List<ApiRoute>
)
