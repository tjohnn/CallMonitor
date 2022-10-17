package com.tjohnn.callmonitor.server

import com.nhaarman.mockitokotlin2.given
import com.tjohnn.callmonitor.callLog1
import com.tjohnn.callmonitor.callLog2
import com.tjohnn.callmonitor.domain.call.GetOngoingCallStatusUseCase
import com.tjohnn.callmonitor.domain.call.QueryCallLogsUseCase
import com.tjohnn.callmonitor.domain.call.model.OngoingCall
import com.tjohnn.callmonitor.domain.serverinformation.GetServerInformationUseCase
import com.tjohnn.callmonitor.server.mapper.CallLogResponseMapper
import com.tjohnn.callmonitor.server.mapper.OngoingCallResponseMapper
import com.tjohnn.callmonitor.server.model.ApiRoute
import com.tjohnn.callmonitor.server.model.CallLogResponseModel
import com.tjohnn.callmonitor.server.model.OngoingCallStatusResponseModel
import com.tjohnn.callmonitor.server.model.ServerInformationResponseModel
import com.tjohnn.callmonitor.serverInformation
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiControllerTest {

    private lateinit var classUnderTest: ApiController

    @Mock
    private lateinit var getServerInformationUseCase: GetServerInformationUseCase

    @Mock
    private lateinit var queryCallLogsUseCase: QueryCallLogsUseCase

    @Mock
    private lateinit var getOngoingCallStatusUseCase: GetOngoingCallStatusUseCase

    @Mock
    private lateinit var ongoingCallResponseMapper: OngoingCallResponseMapper

    @Mock
    private lateinit var callLogResponseMapper: CallLogResponseMapper

    @Before
    fun setup() {
        classUnderTest = ApiController(
            getServerInformationUseCase,
            queryCallLogsUseCase,
            getOngoingCallStatusUseCase,
            ongoingCallResponseMapper,
            callLogResponseMapper
        )
    }

    @Test
    fun `Given list of routes and server information When getServiceInformation then returns expected ServerInformationResponseModel`() {
        runBlocking {
            val routes = listOf("/", "/status", "/log")
            val baseUrl = "http://${serverInformation.ipAddress}:${serverInformation.port}"
            val apiRoutes = listOf(
                ApiRoute("status", "$baseUrl/status"),
                ApiRoute("log", "$baseUrl/log")
            )
            val expectedResult =
                ServerInformationResponseModel(serverInformation.timeStarted.toString(), apiRoutes)

            // Given
            given(getServerInformationUseCase.execute(Unit)).willReturn(flowOf(serverInformation))

            // When
            val actualResult = classUnderTest.getServiceInformation(routes)

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `When getOngoingCallStatus then returns expected OngoingCallStatusResponseModel`() {
        runBlocking {
            val ongoingCallStatus = OngoingCall.None
            val expectedResult = OngoingCallStatusResponseModel(
                hasOngoingCall = false,
                phoneNumber = null,
                callerName = null
            )

            // Given
            given(getOngoingCallStatusUseCase.execute(Unit)).willReturn(flowOf(ongoingCallStatus))
            given(ongoingCallResponseMapper.map(ongoingCallStatus)).willReturn(expectedResult)

            // When
            val actualResult = classUnderTest.getOngoingCallStatus()

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `When getCallLogs then returns expected call logs`() {
        runBlocking {
            val expectedResult = listOf(
                CallLogResponseModel(
                    startTime = callLog1.startTime.toString(),
                    durationInSeconds = 50,
                    phoneNumber = callLog1.phoneNumber,
                    callerName = callLog1.callerName,
                    timesQueried = callLog1.timesQueried
                ),
                CallLogResponseModel(
                    startTime = callLog2.startTime.toString(),
                    durationInSeconds = 50,
                    phoneNumber = callLog2.phoneNumber,
                    callerName = callLog2.callerName,
                    timesQueried = callLog2.timesQueried
                )
            )

            // Given
            given(queryCallLogsUseCase.execute(Unit)).willReturn(flowOf(listOf(callLog1, callLog2)))
            given(callLogResponseMapper.map(callLog1)).willReturn(expectedResult[0])
            given(callLogResponseMapper.map(callLog2)).willReturn(expectedResult[1])

            // When
            val actualResult = classUnderTest.getCallLogs()

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }
}
