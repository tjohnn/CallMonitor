package com.tjohnn.callmonitor.data.server

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.tjohnn.callmonitor.data.cache.Cache
import com.tjohnn.callmonitor.data.server.model.ServerInformationDataModel
import com.tjohnn.callmonitor.date1
import com.tjohnn.callmonitor.server.SERVER_PORT
import com.tjohnn.callmonitor.time.TimeProvider
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

private const val ipAddress = "127.0.0.1"

@RunWith(MockitoJUnitRunner::class)
class ServerInformationDataSourceTest {

    private lateinit var classUnderTest: ServerInformationDataSource

    @Mock
    private lateinit var serverInformationCache: Cache<ServerInformationDataModel>

    @Mock
    private lateinit var localIpAddressFacade: LocalIpAddressFacade

    @Mock
    private lateinit var timeProvider: TimeProvider

    @Before
    fun setup() {
        classUnderTest = ServerInformationDataSource(
            serverInformationCache,
            localIpAddressFacade,
            timeProvider
        )
        given(localIpAddressFacade.getSystemLocalIpAddress()).willReturn(ipAddress)
    }

    @Test
    fun `When get Then emitsIfEmpty to cache and return expected flow`() {
        runBlocking {
            // Given
            val initialServerInformation = ServerInformationDataModel(
                ipAddress = ipAddress,
                port = SERVER_PORT,
                isRunning = false,
                timeStarted = null
            )
            val expectedResult = flowOf(initialServerInformation)
            given(localIpAddressFacade.getSystemLocalIpAddress()).willReturn(ipAddress)
            given(serverInformationCache.flow).willReturn(expectedResult)

            // When
            val actualResult = classUnderTest.get()

            // Then
            verify(serverInformationCache).saveIfEmpty(initialServerInformation)
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `Given server is running When updateServerRunningState Then updates server information correctly`() {
        runBlocking {
            // Given
            val initialServerInformation = ServerInformationDataModel(
                ipAddress = ipAddress,
                port = SERVER_PORT,
                isRunning = false,
                timeStarted = null
            )
            val newServerInformation = ServerInformationDataModel(
                ipAddress = ipAddress,
                port = SERVER_PORT,
                isRunning = true,
                timeStarted = date1
            )
            val serverInformationFlow = flowOf(initialServerInformation)
            given(serverInformationCache.flow).willReturn(serverInformationFlow)
            given(timeProvider.getCurrentTime()).willReturn(date1)

            // When
            classUnderTest.updateServerRunningState(true)

            // Then
            verify(serverInformationCache).save(newServerInformation)
        }
    }

    @Test
    fun `Given server is not running When updateServerRunningState Then updates server information correctly`() {
        runBlocking {
            // Given
            val initialServerInformation = ServerInformationDataModel(
                ipAddress = ipAddress,
                port = SERVER_PORT,
                isRunning = false,
                timeStarted = null
            )
            val serverInformation = ServerInformationDataModel(
                ipAddress = ipAddress,
                port = SERVER_PORT,
                isRunning = true,
                timeStarted = date1
            )
            val serverInformationFlow = flowOf(serverInformation)
            given(serverInformationCache.flow).willReturn(serverInformationFlow)

            // When
            classUnderTest.updateServerRunningState(false)

            // Then
            verify(serverInformationCache).save(initialServerInformation)
        }
    }
}
