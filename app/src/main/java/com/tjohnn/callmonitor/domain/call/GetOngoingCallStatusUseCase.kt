package com.tjohnn.callmonitor.domain.call

import com.tjohnn.callmonitor.domain.base.BaseUseCase
import com.tjohnn.callmonitor.domain.call.model.OngoingCall
import com.tjohnn.callmonitor.domain.call.repository.GetOngoingCallRepository
import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import kotlinx.coroutines.flow.Flow

class GetOngoingCallStatusUseCase(
    private val getOngoingCallRepository: GetOngoingCallRepository,
    dispatcher: Dispatcher
) : BaseUseCase<Unit, OngoingCall>(dispatcher) {
    override suspend fun execute(input: Unit): Flow<OngoingCall> {
        return getOngoingCallRepository.getOngoingCall()
    }
}
