package com.tjohnn.callmonitor.presentation.calllogs

import com.tjohnn.callmonitor.domain.call.model.CallLog
import com.tjohnn.callmonitor.presentation.base.ViewState

data class CallLogsViewState(
    val isLoadingData: Boolean = false,
    val callLogs: Collection<CallLog> = emptyList(),
    val error: Throwable? = null
) : ViewState
