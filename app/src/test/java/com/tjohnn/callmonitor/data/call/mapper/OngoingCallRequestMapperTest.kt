package com.tjohnn.callmonitor.data.call.mapper

import com.tjohnn.callmonitor.data.call.model.OngoingCallDataModel
import com.tjohnn.callmonitor.domain.call.model.SaveCallStartedRequest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.Date

@RunWith(Parameterized::class)
class OngoingCallRequestMapperTest(
    private val givenInput: SaveCallStartedRequest,
    private val expectedResult: OngoingCallDataModel
) {
    companion object {
        private val startDate = Date(2022, 8, 22, 12, 0, 0)

        @JvmStatic
        @Parameterized.Parameters(name = "Given {0} When map Then returns {1}")
        fun parameters(): Collection<Array<*>> = listOf(
            arrayOf(
                SaveCallStartedRequest(
                    time = startDate,
                    phoneNumber = "+49 1010101010",
                    callerName = "John"
                ),
                OngoingCallDataModel.Ongoing(
                    startTime = startDate,
                    phoneNumber = "+49 1010101010",
                    callerName = "John"
                )
            )
        )
    }

    private val classUnderTest = OngoingCallRequestMapper()

    @Test
    fun `Given OngoingCallDataModel When map Then returns the expected OngoingCall`() {
        // When
        val actualValue = classUnderTest.map(givenInput)

        // Then
        assertEquals(expectedResult, actualValue)
    }
}
