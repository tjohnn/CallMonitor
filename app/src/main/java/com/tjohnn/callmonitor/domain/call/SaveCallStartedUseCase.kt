package com.tjohnn.callmonitor.domain.call

import com.tjohnn.callmonitor.domain.base.BaseUseCase
import com.tjohnn.callmonitor.domain.call.model.SaveCallStartedRequest
import com.tjohnn.callmonitor.domain.call.repository.SaveCallStartedRepository
import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SaveCallStartedUseCase(
    private val saveCallStartedRepository: SaveCallStartedRepository,
    dispatcher: Dispatcher
) : BaseUseCase<SaveCallStartedRequest, Unit>(dispatcher) {
    override suspend fun execute(input: SaveCallStartedRequest): Flow<Unit> {
        return flowOf(saveCallStartedRepository.saveCallStarted(input))
    }
}
