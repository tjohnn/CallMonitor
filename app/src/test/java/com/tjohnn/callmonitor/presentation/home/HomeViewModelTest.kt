package com.tjohnn.callmonitor.presentation.home

import com.nhaarman.mockitokotlin2.given
import com.tjohnn.callmonitor.dispatcher.MainDispatcherRule
import com.tjohnn.callmonitor.serverInformationResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var classUnderTest: HomeViewModel

    @Mock
    private lateinit var homeIntentProcessor: HomeIntentProcessor

    @Mock
    private lateinit var homeViewStateReducer: HomeViewStateReducer

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        classUnderTest = HomeViewModel(
            homeIntentProcessor,
            homeViewStateReducer
        )
    }

    @Test
    fun `Given OnViewCreated When processIntent Then updates viewstate correctly`() {
        runBlocking {
            // Given
            val intent = HomeViewIntent.OnViewCreated
            val expectedViewStates = listOf(
                HomeViewState(),
                HomeViewState(isLoadingData = true),
                HomeViewState(
                    isServerRunning = serverInformationResult.isServerRunning,
                    ipAddress = serverInformationResult.serverIpAddress,
                    port = serverInformationResult.serverPort.toString()
                )
            )
            given(homeIntentProcessor.intentToResult(intent))
                .willReturn(flowOf(HomeViewResult.IsLoading, serverInformationResult))
            given(homeViewStateReducer.reduce(expectedViewStates[0], HomeViewResult.IsLoading))
                .willReturn(expectedViewStates[1])
            given(homeViewStateReducer.reduce(expectedViewStates[1], serverInformationResult))
                .willReturn(expectedViewStates[2])

            // When
            classUnderTest.processIntent(intent)
            val actualResult = classUnderTest.viewState.first()

            assertEquals(expectedViewStates.last(), actualResult)
        }
    }
}
