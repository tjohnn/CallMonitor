package com.tjohnn.callmonitor.presentation.calllogs

import com.tjohnn.callmonitor.presentation.base.ViewStateReducer

class CallLogsViewStateReducer : ViewStateReducer<CallLogsViewResult, CallLogsViewState> {

    override fun reduce(
        previousState: CallLogsViewState,
        result: CallLogsViewResult
    ): CallLogsViewState {
        return CallLogsViewState(
            isLoadingData = reduceIsLoadingData(result),
            callLogs = (result as? CallLogsViewResult.CallLogs)?.callLogs ?: previousState.callLogs,
            error = (result as? CallLogsViewResult.Error)?.error
        )
    }

    private fun reduceIsLoadingData(result: CallLogsViewResult): Boolean {
        return when (result) {
            CallLogsViewResult.IsLoading -> true
            is CallLogsViewResult.CallLogs -> false
            is CallLogsViewResult.Error -> false
        }
    }
}
