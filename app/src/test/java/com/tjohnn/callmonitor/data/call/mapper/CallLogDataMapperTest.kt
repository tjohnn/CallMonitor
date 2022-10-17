package com.tjohnn.callmonitor.data.call.mapper

import com.tjohnn.callmonitor.callLog1
import com.tjohnn.callmonitor.callLog2
import com.tjohnn.callmonitor.data.call.model.CallLogDataModel
import com.tjohnn.callmonitor.dataCallLog1
import com.tjohnn.callmonitor.dataCallLog2
import com.tjohnn.callmonitor.domain.call.model.CallLog
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class CallLogDataMapperTest(
    private val givenInput: CallLogDataModel,
    private val expectedResult: CallLog
) {
    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "Given {0} When map Then returns {1}")
        fun parameters(): Collection<Array<*>> = listOf(
            arrayOf(dataCallLog1, callLog1),
            arrayOf(dataCallLog2, callLog2)
        )
    }

    private val classUnderTest = CallLogDataMapper()

    @Test
    fun `Given CallLogDataModel When map Then returns the expected CallLog`() {
        // When
        val actualValue = classUnderTest.map(givenInput)

        // Then
        assertEquals(expectedResult, actualValue)
    }
}
