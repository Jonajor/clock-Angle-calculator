package service

import input.InvalidTimeFormatException
import input.TimeInputProvider
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import model.ClockAngle
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MockKExtension::class)
class ClockServiceTest {

    @InjectMockKs
    lateinit var clockService: ClockService

    @MockK
    lateinit var timeInputProvider: TimeInputProvider

    @MockK
    lateinit var clockAngle: ClockAngle

    @ParameterizedTest
    @CsvSource(
        "00 00, 0.0",
        "06 00, 90.0",
        "02:10, 32.5",
        "15-00, 225.0",
        "17/00, 255.0",
        "18ˆ00, 270.0",
        "23;00, 345.0",
        "03 15, 48.75"
    )
    fun `Given input of hour and minute with different formats then should handle and print correctly the angle`(
        input: String, expected: Double
    ) {
        every { timeInputProvider.getTime(any()) } returns getHourAndMin(input)

        val clock = clockService.handleHourAndMinute(input)

        assertEquals(expected, clock.hourAngle)
    }


    @ParameterizedTest
    @ValueSource(strings = ["24 00", "12:60"])
    fun `Given an input with values bigger than 23 hour or bigger than 59 minutes, then should return error`(input: String) {
        every { timeInputProvider.getTime(any()) } returns getHourAndMin(input)

        val exception = assertThrows(InvalidTimeFormatException::class.java) {
            clockService.handleHourAndMinute(input)
        }

        assertEquals("Invalid hours or minutes value", exception.message)
    }

    @Test
    fun `Given an input with letters, then should return error`() {
        every { timeInputProvider.getTime(any()) } throws NumberFormatException()

        assertThrows(NumberFormatException::class.java) {
            clockService.handleHourAndMinute("abcd")
        }
    }

    @Test
    fun `Given an input with one argument, then should return error`() {
        every { timeInputProvider.getTime(any()) } throws InvalidTimeFormatException("Invalid time format")

        val exception = assertThrows(InvalidTimeFormatException::class.java) {
            clockService.handleHourAndMinute("20")
        }

        assertEquals("Invalid time format", exception.message)
    }


    private fun getHourAndMin(input: String): Pair<Int, Int> {
        val parts = input.split(Regex("[:\\-/ˆ; ]"))
        val hour = parts[0].toInt()
        val minute = parts[1].toInt()
        return Pair(hour, minute)
    }

}