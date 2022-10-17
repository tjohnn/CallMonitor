package com.tjohnn.callmonitor.data.call

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import com.tjohnn.callmonitor.callLog1
import com.tjohnn.callmonitor.data.call.mapper.CallLogDataMapper
import com.tjohnn.callmonitor.data.call.mapper.OngoingCallDataMapper
import com.tjohnn.callmonitor.data.call.model.CallLogRequest
import com.tjohnn.callmonitor.data.call.model.OngoingCallDataModel
import com.tjohnn.callmonitor.dataCallLog1
import com.tjohnn.callmonitor.date1
import com.tjohnn.callmonitor.date2
import com.tjohnn.callmonitor.domain.call.model.OngoingCall
import com.tjohnn.callmonitor.domain.call.model.SaveCallEndedRequest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CallDataRepositoryTest {

    private lateinit var classUnderTest: CallDataRepository

    @Mock
    private lateinit var ongoingCallDataSource: OngoingCallDataSource

    @Mock
    private lateinit var callLogsDataSource: CallLogsDataSource

    @Mock
    private lateinit var callLogDataMapper: CallLogDataMapper

    @Mock
    private lateinit var ongoingCallDataMapper: OngoingCallDataMapper

    @Before
    fun setup() {
        classUnderTest = CallDataRepository(
            ongoingCallDataSource,
            callLogsDataSource,
            callLogDataMapper,
            ongoingCallDataMapper
        )
    }

    @Test
    fun `When getCallLogs Then returns the expected saved call logs`() {
        runBlocking {
            // Given
            val expectedResult = listOf(callLog1)
            given(callLogsDataSource.get()).willReturn(flowOf(listOf(dataCallLog1)))
            given(callLogDataMapper.map(dataCallLog1)).willReturn(callLog1)

            // When
            val actualResult = classUnderTest.getCallLogs().first()

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `When queryCallLogs Then increments query counts and returns the expected saved call logs`() {
        runBlocking {
            // Given
            val expectedResult = listOf(callLog1)
            given(callLogsDataSource.get()).willReturn(flowOf(listOf(dataCallLog1)))
            given(callLogDataMapper.map(dataCallLog1)).willReturn(callLog1)

            // When
            val actualResult = classUnderTest.queryCallLogs().first()

            // Then
            verify(callLogsDataSource).incrementQueryCounts()
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `When getOngoing calls Then returns the expected ongoing call`() {
        runBlocking {
            // Given
            val expectedResult = OngoingCall.None
            given(ongoingCallDataSource.get()).willReturn(flowOf(OngoingCallDataModel.None))
            given(ongoingCallDataMapper.map(OngoingCallDataModel.None)).willReturn(OngoingCall.None)

            // When
            val actualResult = classUnderTest.getOngoingCall().first()

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `When saveCallStarted calls Then saves an ongoing call`() {
        runBlocking {
            // Given
            val expectedResult = OngoingCall.None
            given(ongoingCallDataSource.get()).willReturn(flowOf(OngoingCallDataModel.None))
            given(ongoingCallDataMapper.map(OngoingCallDataModel.None)).willReturn(OngoingCall.None)

            // When
            val actualResult = classUnderTest.getOngoingCall().first()

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `Given an ongoing call When saveCallEnded Then logs the ongoing call ending`() {
        runBlocking {
            // Given
            val ongoingCall = OngoingCallDataModel.Ongoing(
                startTime = date1,
                phoneNumber = "+49 1111111111",
                callerName = "Doe"
            )
            val callEndRequest = SaveCallEndedRequest(
                time = date2,
                phoneNumber = "+49 1111111111"
            )
            val request = CallLogRequest(ongoingCall, callEndRequest)
            given(ongoingCallDataSource.get()).willReturn(flowOf(ongoingCall))

            // When
            classUnderTest.saveCallEnded(callEndRequest)

            // Then
            verify(callLogsDataSource).logCall(request)
        }
    }

    @Test
    fun `Given no ongoing call When saveCallEnded Then logs nothing`() {
        runBlocking {
            // Given
            val ongoingCall = OngoingCallDataModel.None
            val callEndRequest = SaveCallEndedRequest(
                time = date2,
                phoneNumber = "+49 1111111111"
            )
            given(ongoingCallDataSource.get()).willReturn(flowOf(ongoingCall))

            // When
            classUnderTest.saveCallEnded(callEndRequest)

            // Then
            verify(callLogsDataSource, never()).logCall(any())
        }
    }
}
