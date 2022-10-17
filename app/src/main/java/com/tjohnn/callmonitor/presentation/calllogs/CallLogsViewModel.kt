package com.tjohnn.callmonitor.presentation.calllogs

import com.tjohnn.callmonitor.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CallLogsViewModel @Inject constructor(
    callLogsIntentProcessor: CallLogsIntentProcessor,
    callLogsViewStateReducer: CallLogsViewStateReducer
) : BaseViewModel<CallLogsViewIntent, CallLogsViewResult, CallLogsViewState>(
    callLogsIntentProcessor,
    callLogsViewStateReducer
) {
    override fun initialState() = CallLogsViewState()
}
