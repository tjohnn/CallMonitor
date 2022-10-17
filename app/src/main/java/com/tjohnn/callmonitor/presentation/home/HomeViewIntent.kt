package com.tjohnn.callmonitor.presentation.home

import com.tjohnn.callmonitor.presentation.base.ViewIntent

sealed interface HomeViewIntent : ViewIntent {
    object OnViewCreated : HomeViewIntent
    object OnStartServer : HomeViewIntent
    object OnStopServer : HomeViewIntent
    object OnShowCallLogs : HomeViewIntent
}
