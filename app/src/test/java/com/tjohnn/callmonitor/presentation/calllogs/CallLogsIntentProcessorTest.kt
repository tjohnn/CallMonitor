package com.tjohnn.callmonitor.presentation.calllogs

import com.tjohnn.callmonitor.callLog1
import com.tjohnn.callmonitor.domain.call.GetCallLogsUseCase
import com.tjohnn.callmonitor.presentation.usecase.givenFailedExecution
import com.tjohnn.callmonitor.presentation.usecase.givenSuccessfulExecution
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CallLogsIntentProcessorTest {

    private lateinit var classUnderTest: CallLogsIntentProcessor

    @Mock
    private lateinit var getCallLogsUseCase: GetCallLogsUseCase

    @Before
    fun setup() {
        classUnderTest = CallLogsIntentProcessor(getCallLogsUseCase)
    }

    @Test
    fun `Given OnViewCreated and successful usecase When intentToResult Then returns a flow of expected result`() {
        runBlocking {
            // Given
            val intent = CallLogsViewIntent.OnViewCreated
            val callLogs = listOf(callLog1)
            val expectedResult =
                listOf(CallLogsViewResult.IsLoading, CallLogsViewResult.CallLogs(callLogs))

            getCallLogsUseCase.givenSuccessfulExecution(Unit, callLogs)

            // When
            val actualResult = mutableListOf<CallLogsViewResult>()
            classUnderTest.intentToResult(intent).toList(actualResult)

            assertEquals(expectedResult, actualResult)
        }
    }

    @Test
    fun `Given OnViewCreated and failed usecase When intentToResult Then returns a flow of expected result`() {
        runBlocking {
            // Given
            val intent = CallLogsViewIntent.OnViewCreated
            val error = Throwable()
            val expectedResult = listOf(CallLogsViewResult.IsLoading, CallLogsViewResult.Error(error))

            getCallLogsUseCase.givenFailedExecution(Unit, error)

            // When
            val actualResult = mutableListOf<CallLogsViewResult>()
            classUnderTest.intentToResult(intent).toList(actualResult)

            assertEquals(expectedResult, actualResult)
        }
    }
}
