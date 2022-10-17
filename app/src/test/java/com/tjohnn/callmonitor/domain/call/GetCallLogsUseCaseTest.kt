package com.tjohnn.callmonitor.domain.call

import com.nhaarman.mockitokotlin2.given
import com.tjohnn.callmonitor.callLog1
import com.tjohnn.callmonitor.dispatcher.testDispatcher
import com.tjohnn.callmonitor.domain.call.repository.GetCallLogsRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCallLogsUseCaseTest {

    private lateinit var classUnderTest: GetCallLogsUseCase

    @Mock
    private lateinit var getCallLogsRepository: GetCallLogsRepository

    @Before
    fun setup() {
        classUnderTest = GetCallLogsUseCase(
            getCallLogsRepository,
            testDispatcher
        )
    }

    @Test
    fun `When execute Then returns the expected call logs`() {
        runBlocking {
            // Given
            val expectedResult = flowOf(listOf(callLog1))
            given(getCallLogsRepository.getCallLogs()).willReturn(expectedResult)

            // When
            val actualResult = classUnderTest.execute(Unit)

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }
}
