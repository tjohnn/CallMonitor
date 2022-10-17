package com.tjohnn.callmonitor.domain.call

import com.nhaarman.mockitokotlin2.verify
import com.tjohnn.callmonitor.date1
import com.tjohnn.callmonitor.domain.call.model.SaveCallStartedRequest
import com.tjohnn.callmonitor.domain.call.repository.SaveCallStartedRepository
import com.tjohnn.callmonitor.testDispatcher
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveCallStartedUseCaseTest {

    private lateinit var classUnderTest: SaveCallStartedUseCase

    @Mock
    private lateinit var saveCallStartedRepository: SaveCallStartedRepository

    @Before
    fun setup() {
        classUnderTest = SaveCallStartedUseCase(
            saveCallStartedRepository,
            testDispatcher
        )
    }

    @Test
    fun `When execute Then saves call started`() {
        runBlocking {
            // Given
            val request = SaveCallStartedRequest(
                phoneNumber = "90390003",
                callerName = "John",
                time = date1
            )

            // When
            classUnderTest.execute(request)

            // Then
            verify(saveCallStartedRepository).saveCallStarted(request)
        }
    }
}
