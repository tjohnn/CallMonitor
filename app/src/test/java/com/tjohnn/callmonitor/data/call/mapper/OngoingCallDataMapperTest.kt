package com.tjohnn.callmonitor.data.call.mapper

import com.tjohnn.callmonitor.data.call.model.OngoingCallDataModel
import com.tjohnn.callmonitor.domain.call.model.OngoingCall
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.Date

@RunWith(Parameterized::class)
class OngoingCallDataMapperTest(
    private val givenInput: OngoingCallDataModel,
    private val expectedResult: OngoingCall
) {
    companion object {
        private val startDate = Date(2022, 8, 22, 12, 0, 0)

        @JvmStatic
        @Parameterized.Parameters(name = "Given {0} When map Then returns {1}")
        fun parameters(): Collection<Array<*>> = listOf(
            arrayOf(
                OngoingCallDataModel.Ongoing(
                    startTime = startDate,
                    phoneNumber = "+49 1010101010",
                    callerName = "John"
                ),
                OngoingCall.Ongoing(
                    startTime = startDate,
                    phoneNumber = "+49 1010101010",
                    callerName = "John"
                )
            ),
            arrayOf(
                OngoingCallDataModel.None,
                OngoingCall.None
            )
        )
    }

    private val classUnderTest = OngoingCallDataMapper()

    @Test
    fun `Given OngoingCallDataModel When map Then returns the expected OngoingCall`() {
        // When
        val actualValue = classUnderTest.map(givenInput)

        // Then
        assertEquals(expectedResult, actualValue)
    }
}
