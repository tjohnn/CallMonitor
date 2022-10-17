package com.tjohnn.callmonitor.presentation.calllogs

import com.tjohnn.callmonitor.domain.call.model.CallLog
import com.tjohnn.callmonitor.presentation.base.ViewResult

sealed interface CallLogsViewResult : ViewResult {
    data class CallLogs(
        val callLogs: Collection<CallLog>
    ) : CallLogsViewResult

    object IsLoading : CallLogsViewResult
    data class Error(val error: Throwable) : CallLogsViewResult
}
