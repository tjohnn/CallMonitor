package com.tjohnn.callmonitor.presentation.home

import com.tjohnn.callmonitor.domain.serverinformation.GetServerInformationUseCase
import com.tjohnn.callmonitor.presentation.base.IntentProcessor
import com.tjohnn.callmonitor.presentation.home.mapper.ServerInformationToHomeResultMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOf
import timber.log.Timber

class HomeIntentProcessor(
    private val getServerInformationUseCase: GetServerInformationUseCase,
    private val serverInformationToHomeResultMapper: ServerInformationToHomeResultMapper
) : IntentProcessor<HomeViewIntent, HomeViewResult> {
    override suspend fun intentToResult(intent: HomeViewIntent): Flow<HomeViewResult> {
        return when (intent) {
            HomeViewIntent.OnStartServer -> flowOf(HomeViewResult.StartServer)
            HomeViewIntent.OnStopServer -> flowOf(HomeViewResult.StopServer)
            HomeViewIntent.OnShowCallLogs -> flowOf(HomeViewResult.NavigateToCallLogs)
            HomeViewIntent.OnViewCreated -> getCurrentServerState()
        }
    }

    private suspend fun getCurrentServerState(): Flow<HomeViewResult> {
        return channelFlow {
            trySend(HomeViewResult.IsLoading)
            getServerInformationUseCase.executeInBackground(
                input = Unit,
                callback = { trySend(serverInformationToHomeResultMapper.toResult(it)) },
                onError = { error ->
                    Timber.e(error)
                    trySend(HomeViewResult.Error(error))
                }
            )
        }
    }
}
