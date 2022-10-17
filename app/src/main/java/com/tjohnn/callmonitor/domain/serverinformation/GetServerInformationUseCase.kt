package com.tjohnn.callmonitor.domain.serverinformation

import com.tjohnn.callmonitor.domain.base.BaseUseCase
import com.tjohnn.callmonitor.domain.dispatcher.Dispatcher
import kotlinx.coroutines.flow.Flow

class GetServerInformationUseCase(
    private val serverInformationRepository: ServerInformationRepository,
    dispatcher: Dispatcher
) : BaseUseCase<Unit, ServerInformation>(dispatcher) {
    override suspend fun execute(input: Unit): Flow<ServerInformation> {
        return serverInformationRepository.getServerInformation()
    }
}
