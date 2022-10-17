package com.tjohnn.callmonitor.presentation.home

import com.tjohnn.callmonitor.presentation.base.ViewResult

sealed interface HomeViewResult : ViewResult {
    data class ServerInformation(
        val serverIpAddress: String,
        val serverPort: Int,
        val isServerRunning: Boolean
    ) : HomeViewResult

    object IsLoading : HomeViewResult
    object NavigateToCallLogs : HomeViewResult
    object StartServer : HomeViewResult
    object StopServer : HomeViewResult
    data class Error(val error: Throwable) : HomeViewResult
}
