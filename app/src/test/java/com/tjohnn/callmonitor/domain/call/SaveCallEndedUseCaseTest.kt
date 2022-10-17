package com.tjohnn.callmonitor.domain.call

import com.nhaarman.mockitokotlin2.verify
import com.tjohnn.callmonitor.date1
import com.tjohnn.callmonitor.dispatcher.testDispatcher
import com.tjohnn.callmonitor.domain.call.model.SaveCallEndedRequest
import com.tjohnn.callmonitor.domain.call.repository.SaveCallEndedRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SaveCallEndedUseCaseTest {

    private lateinit var classUnderTest: SaveCallEndedUseCase

    @Mock
    private lateinit var saveCallEndedRepository: SaveCallEndedRepository

    @Before
    fun setup() {
        classUnderTest = SaveCallEndedUseCase(
            saveCallEndedRepository,
            testDispatcher
        )
    }

    @Test
    fun `When execute Then saves call ended`() {
        runBlocking {
            // Given
            val request = SaveCallEndedRequest(
                phoneNumber = "90390003",
                time = date1
            )

            // When
            classUnderTest.execute(request)

            // Then
            verify(saveCallEndedRepository).saveCallEnded(request)
        }
    }
}
