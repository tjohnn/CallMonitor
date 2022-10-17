package com.tjohnn.callmonitor.server.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiRoute(
    val name: String,
    val uri: String
)
