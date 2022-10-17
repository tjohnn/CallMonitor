package com.tjohnn.callmonitor.presentation.home

sealed interface HomeViewEffect {
    object NavigateToCallLogs : HomeViewEffect
    object StartServer : HomeViewEffect
    object StopServer : HomeViewEffect
}
