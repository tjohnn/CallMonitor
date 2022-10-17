package com.tjohnn.callmonitor.presentation.home

import com.tjohnn.callmonitor.presentation.base.ViewStateReducer

class HomeViewStateReducer : ViewStateReducer<HomeViewResult, HomeViewState> {

    override fun reduce(previousState: HomeViewState, result: HomeViewResult): HomeViewState {
        return HomeViewState(
            isServerRunning = reduceIsServerRunning(previousState, result),
            isLoadingData = reduceIsLoadingData(previousState, result),
            ipAddress = reduceIpAddress(previousState, result),
            port = reduceServerPort(previousState, result),
            viewEffect = reduceViewEffect(result),
            error = (result as? HomeViewResult.Error)?.error
        )
    }

    private fun reduceIpAddress(previousState: HomeViewState, result: HomeViewResult): String {
        return when (result) {
            is HomeViewResult.ServerInformation -> result.serverIpAddress
            else -> previousState.ipAddress
        }
    }

    private fun reduceServerPort(previousState: HomeViewState, result: HomeViewResult): String {
        return when (result) {
            is HomeViewResult.ServerInformation -> result.serverPort.toString()
            else -> previousState.port
        }
    }

    private fun reduceIsServerRunning(
        previousState: HomeViewState,
        result: HomeViewResult
    ): Boolean {
        return when (result) {
            is HomeViewResult.ServerInformation -> result.isServerRunning
            else -> previousState.isServerRunning
        }
    }

    private fun reduceIsLoadingData(
        previousState: HomeViewState,
        result: HomeViewResult
    ): Boolean {
        return when (result) {
            HomeViewResult.IsLoading -> true
            is HomeViewResult.ServerInformation -> false
            is HomeViewResult.Error -> false
            else -> previousState.isLoadingData
        }
    }

    private fun reduceViewEffect(result: HomeViewResult): HomeViewEffect? {
        return when (result) {
            HomeViewResult.NavigateToCallLogs -> HomeViewEffect.NavigateToCallLogs
            HomeViewResult.StartServer -> HomeViewEffect.StartServer
            HomeViewResult.StopServer -> HomeViewEffect.StopServer
            else -> null
        }
    }
}
