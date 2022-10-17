package com.tjohnn.callmonitor.presentation.calllogs

import com.tjohnn.callmonitor.presentation.base.ViewIntent

sealed interface CallLogsViewIntent : ViewIntent {
    object OnViewCreated : CallLogsViewIntent
}
