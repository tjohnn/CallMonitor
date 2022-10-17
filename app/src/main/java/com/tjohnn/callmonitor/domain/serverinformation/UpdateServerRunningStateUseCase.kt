package com.tjohnn.callmonitor.domain.serverinformation

import com.tjohnn.callmonitor.domain.base.BaseUseCase
import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UpdateServerRunningStateUseCase(
    private val updateServerRunningStateRepository: UpdateServerRunningStateRepository,
    dispatcher: Dispatcher
) : BaseUseCase<Boolean, Unit>(dispatcher) {
    override suspend fun execute(input: Boolean): Flow<Unit> {
        return flowOf(updateServerRunningStateRepository.updateServerRunningState(input))
    }
}
