package com.tjohnn.callmonitor.domain.call

import com.tjohnn.callmonitor.domain.base.BaseUseCase
import com.tjohnn.callmonitor.domain.call.model.SaveCallEndedRequest
import com.tjohnn.callmonitor.domain.call.repository.SaveCallEndedRepository
import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SaveCallEndedUseCase(
    private val saveCallEndedRepository: SaveCallEndedRepository,
    dispatcher: Dispatcher
) : BaseUseCase<SaveCallEndedRequest, Unit>(dispatcher) {
    override suspend fun execute(input: SaveCallEndedRequest): Flow<Unit> {
        return flowOf(saveCallEndedRepository.saveCallEnded(input))
    }
}
