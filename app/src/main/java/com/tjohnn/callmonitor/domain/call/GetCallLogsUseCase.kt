package com.tjohnn.callmonitor.domain.call

import com.tjohnn.callmonitor.domain.base.BaseUseCase
import com.tjohnn.callmonitor.domain.call.model.CallLog
import com.tjohnn.callmonitor.domain.call.repository.GetCallLogsRepository
import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import kotlinx.coroutines.flow.Flow

class GetCallLogsUseCase(
    private val getCallLogsRepository: GetCallLogsRepository,
    dispatcher: Dispatcher
) : BaseUseCase<Unit, Collection<CallLog>>(dispatcher) {
    override suspend fun execute(input: Unit): Flow<Collection<CallLog>> {
        return getCallLogsRepository.getCallLogs()
    }
}
