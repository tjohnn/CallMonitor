package com.tjohnn.callmonitor.presentation.calllogs

import com.tjohnn.callmonitor.domain.call.GetCallLogsUseCase
import com.tjohnn.callmonitor.presentation.base.IntentProcessor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import timber.log.Timber

class CallLogsIntentProcessor(
    private val getCallLogsUseCase: GetCallLogsUseCase
) : IntentProcessor<CallLogsViewIntent, CallLogsViewResult> {
    override suspend fun intentToResult(intent: CallLogsViewIntent): Flow<CallLogsViewResult> {
        return when (intent) {
            CallLogsViewIntent.OnViewCreated -> getCallLogs()
        }
    }

    private suspend fun getCallLogs(): Flow<CallLogsViewResult> {
        return channelFlow {
            trySend(CallLogsViewResult.IsLoading)
            getCallLogsUseCase.executeInBackground(
                input = Unit,
                callback = { trySend(CallLogsViewResult.CallLogs(it)) },
                onError = { error ->
                    Timber.e(error)
                    trySend(CallLogsViewResult.Error(error))
                }
            )
        }
    }
}
