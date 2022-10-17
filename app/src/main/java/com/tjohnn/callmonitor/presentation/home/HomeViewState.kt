package com.tjohnn.callmonitor.presentation.home

import com.tjohnn.callmonitor.presentation.base.ViewState

data class HomeViewState(
    val isServerRunning: Boolean = false,
    val isLoadingData: Boolean = false,
    val ipAddress: String = "",
    val port: String = "",
    val viewEffect: HomeViewEffect? = null,
    val error: Throwable? = null
) : ViewState
