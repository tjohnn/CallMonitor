package com.tjohnn.callmonitor.domain.serverinformation

import com.nhaarman.mockitokotlin2.given
import com.tjohnn.callmonitor.serverInformation
import com.tjohnn.callmonitor.testDispatcher
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetServerInformationUseCaseTest {

    private lateinit var classUnderTest: GetServerInformationUseCase

    @Mock
    private lateinit var serverInformationRepository: ServerInformationRepository

    @Before
    fun setup() {
        classUnderTest = GetServerInformationUseCase(
            serverInformationRepository,
            testDispatcher
        )
    }

    @Test
    fun `When execute Then returns the expected server information`() {
        runBlocking {
            // Given
            val expectedResult = flowOf(serverInformation)
            given(serverInformationRepository.getServerInformation()).willReturn(expectedResult)

            // When
            val actualResult = classUnderTest.execute(Unit)

            // Then
            assertEquals(expectedResult, actualResult)
        }
    }
}
