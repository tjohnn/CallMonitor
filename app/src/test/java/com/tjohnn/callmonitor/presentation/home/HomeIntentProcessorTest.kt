package com.tjohnn.callmonitor.presentation.home

import com.nhaarman.mockitokotlin2.given
import com.tjohnn.callmonitor.domain.serverinformation.GetServerInformationUseCase
import com.tjohnn.callmonitor.presentation.home.mapper.ServerInformationToHomeResultMapper
import com.tjohnn.callmonitor.presentation.usecase.givenFailedExecution
import com.tjohnn.callmonitor.presentation.usecase.givenSuccessfulExecution
import com.tjohnn.callmonitor.serverInformation
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeIntentProcessorTest {

    private lateinit var classUnderTest: HomeIntentProcessor

    @Mock
    private lateinit var getServerInformationUseCase: GetServerInformationUseCase

    @Mock
    private lateinit var serverInformationToHomeResultMapper: ServerInformationToHomeResultMapper

    @Before
    fun setup() {
        classUnderTest = HomeIntentProcessor(
            getServerInformationUseCase,
            serverInformationToHomeResultMapper
        )
    }

    @Test
    fun `Given OnViewCreated and successful usecase When intentToResult Then returns a flow of expected result`() {
        runBlocking {
            // Given
            val intent = HomeViewIntent.OnViewCreated
            val serverInformationResult = HomeViewResult.ServerInformation(
                serverIpAddress = serverInformation.ipAddress,
                serverPort = serverInformation.port,
                isServerRunning = serverInformation.isRunning
            )
            val expectedResult = listOf(HomeViewResult.IsLoading, serverInformationResult)
            given(serverInformationToHomeResultMapper.toResult(serverInformation))
                .willReturn(serverInformationResult)
            getServerInformationUseCase.givenSuccessfulExecution(Unit, serverInformation)

            // When
            val actualResult = mutableListOf<HomeViewResult>()
            classUnderTest.intentToResult(intent).toList(actualResult)

            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `Given OnViewCreated and failed usecase When intentToResult Then returns a flow of expected result`() {
        runBlocking {
            // Given
            val intent = HomeViewIntent.OnViewCreated
            val error = Throwable()
            val expectedResult = listOf(HomeViewResult.IsLoading, HomeViewResult.Error(error))

            getServerInformationUseCase.givenFailedExecution(Unit, error)

            // When
            val actualResult = mutableListOf<HomeViewResult>()
            classUnderTest.intentToResult(intent).toList(actualResult)

            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `Given OnStartServer When intentToResult Then returns a flow StartServer result`() {
        runBlocking {
            // Given
            val intent = HomeViewIntent.OnStartServer
            val expectedResult = listOf(HomeViewResult.StartServer)

            // When
            val actualResult = mutableListOf<HomeViewResult>()
            classUnderTest.intentToResult(intent).toList(actualResult)

            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `Given OnStopServer When intentToResult Then returns a flow StopServer result`() {
        runBlocking {
            // Given
            val intent = HomeViewIntent.OnStopServer
            val expectedResult = listOf(HomeViewResult.StopServer)

            // When
            val actualResult = mutableListOf<HomeViewResult>()
            classUnderTest.intentToResult(intent).toList(actualResult)

            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `Given OnShowCallLogs When intentToResult Then returns a flow NavigateToCallLogs result`() {
        runBlocking {
            // Given
            val intent = HomeViewIntent.OnShowCallLogs
            val expectedResult = listOf(HomeViewResult.NavigateToCallLogs)

            // When
            val actualResult = mutableListOf<HomeViewResult>()
            classUnderTest.intentToResult(intent).toList(actualResult)

            assertEquals(expectedResult, actualResult)
        }
    }
}
