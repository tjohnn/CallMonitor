package com.tjohnn.callmonitor.data.call.mapper

import com.tjohnn.callmonitor.data.call.model.CallLogDataModel
import com.tjohnn.callmonitor.data.call.model.CallLogRequest
import com.tjohnn.callmonitor.data.call.model.OngoingCallDataModel
import com.tjohnn.callmonitor.domain.call.model.SaveCallEndedRequest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.Date

@RunWith(Parameterized::class)
class CallLogRequestMapperTest(
    private val givenInput: CallLogRequest,
    private val expectedResult: CallLogDataModel
) {
    companion object {
        private val startDate = Date(2022, 8, 22, 12, 0, 0)
        private val endDate = Date(2022, 8, 22, 12, 0, 40)

        @JvmStatic
        @Parameterized.Parameters(name = "Given {0} When map Then returns {1}")
        fun parameters(): Collection<Array<*>> = listOf(
            arrayOf(
                CallLogRequest(
                    ongoingCall = OngoingCallDataModel.Ongoing(
                        startTime = startDate,
                        phoneNumber = "+49 1010101010",
                        callerName = "John"
                    ),
                    callEndRequest = SaveCallEndedRequest(
                        time = endDate,
                        phoneNumber = "+49 1010101010"
                    )
                ),
                CallLogDataModel(
                    startTime = startDate,
                    endTime = endDate,
                    phoneNumber = "+49 1010101010",
                    callerName = "John",
                    timesQueried = 0
                )
            )
        )
    }

    private val classUnderTest = CallLogRequestMapper()

    @Test
    fun `Given CallLogRequest When map Then returns the expected CallLogDataModel`() {
        // When
        val actualValue = classUnderTest.map(givenInput)

        // Then
        assertEquals(expectedResult, actualValue)
    }
}
