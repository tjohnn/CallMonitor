package com.tjohnn.callmonitor.domain.call

import com.nhaarman.mockitokotlin2.given
import com.tjohnn.callmonitor.dispatcher.testDispatcher
import com.tjohnn.callmonitor.domain.call.repository.GetOngoingCallRepository
import com.tjohnn.callmonitor.ongoingCall
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetOngoingCallStatusUseCaseTest {

    private lateinit var classUnderTest: GetOngoingCallStatusUseCase

    @Mock
    private lateinit var getOngoingCallRepository: GetOngoingCallRepository

    @Before
    fun setup() {
        classUnderTest = GetOngoingCallStatusUseCase(
            getOngoingCallRepository,
            testDispatcher
        )
    }

    @Test
    fun `When execute Then returns the expected ongoing calls`() {
        runBlocking {
            // Given
            val expectedResult = flowOf(ongoingCall)
            given(getOngoingCallRepository.getOngoingCall()).willReturn(expectedResult)

            // When
            val actualResult = classUnderTest.execute(Unit)

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }
}
