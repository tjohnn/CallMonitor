package com.tjohnn.callmonitor.presentation.home

import com.tjohnn.callmonitor.serverInformationResult
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class HomeViewStateReducerTest(
    private val previousViewState: HomeViewState,
    private val viewResult: HomeViewResult,
    private val expectedViewState: HomeViewState
) {
    companion object {
        @JvmStatic
        @Parameters(name = "Given previousViewState {0} and viewResult {1} When reduce Then returns expectedViewState {1}")
        fun parameters(): Collection<Array<*>> = listOf(
            arrayOf(
                HomeViewState(viewEffect = HomeViewEffect.StopServer),
                HomeViewResult.IsLoading,
                HomeViewState(isLoadingData = true, viewEffect = null)
            ),
            arrayOf(
                HomeViewState(isLoadingData = true),
                serverInformationResult,
                HomeViewState(
                    isLoadingData = false,
                    isServerRunning = serverInformationResult.isServerRunning,
                    ipAddress = serverInformationResult.serverIpAddress,
                    port = serverInformationResult.serverPort.toString()
                )
            ),
            arrayOf(
                HomeViewState(),
                HomeViewResult.StopServer,
                HomeViewState(viewEffect = HomeViewEffect.StopServer)
            ),
            arrayOf(
                HomeViewState(isLoadingData = true),
                HomeViewResult.NavigateToCallLogs,
                HomeViewState(isLoadingData = true, viewEffect = HomeViewEffect.NavigateToCallLogs)
            ),
            arrayOf(
                HomeViewState(),
                HomeViewResult.StartServer,
                HomeViewState(viewEffect = HomeViewEffect.StartServer)
            )
        )
    }

    private val classUnderTest = HomeViewStateReducer()

    @Test
    fun `Given previous viewstate and a view result When reduce Then returns the expected viewstate`() {
        // When
        val actualValue = classUnderTest.reduce(previousViewState, viewResult)

        // Then
        assertEquals(expectedViewState, actualValue)
    }
}
