package com.tjohnn.callmonitor.data.server.mapper

import com.tjohnn.callmonitor.data.server.model.ServerInformationDataModel
import com.tjohnn.callmonitor.date1
import com.tjohnn.callmonitor.domain.serverinformation.ServerInformation
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ServerInformationDataMapperTest(
    private val givenInput: ServerInformationDataModel,
    private val expectedResult: ServerInformation
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Given {0} When map Then returns {1}")
        fun parameters(): Collection<Array<*>> = listOf(
            arrayOf(
                ServerInformationDataModel(
                    ipAddress = "127.0.0.1",
                    port = 8080,
                    isRunning = false,
                    timeStarted = null
                ),
                ServerInformation(
                    ipAddress = "127.0.0.1",
                    port = 8080,
                    isRunning = false,
                    timeStarted = null
                )
            ),
            arrayOf(
                ServerInformationDataModel(
                    ipAddress = "127.0.0.1",
                    port = 8080,
                    isRunning = true,
                    timeStarted = date1
                ),
                ServerInformation(
                    ipAddress = "127.0.0.1",
                    port = 8080,
                    isRunning = true,
                    timeStarted = date1
                )
            )
        )
    }

    private val classUnderTest = ServerInformationDataMapper()

    @Test
    fun `Given ServerInformationDataModel When map Then returns the expected ServerInformation`() {
        // When
        val actualValue = classUnderTest.map(givenInput)

        // Then
        assertEquals(expectedResult, actualValue)
    }
}
