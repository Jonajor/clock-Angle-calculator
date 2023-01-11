package service

import input.InvalidTimeFormatException
import input.TimeInputProvider
import model.ClockAngle

class ClockService(
    private val timeInputProvider: TimeInputProvider
) {

    fun handleHourAndMinute(time: String): ClockAngle {
        try {
            val (hour, min) = timeInputProvider.getTime(time)
            return calculateAngle(hour, min)
        } catch (ex: InvalidTimeFormatException) {
            throw ex
        } catch (ex: NumberFormatException) {
            throw ex
        }
    }

    private fun calculateAngle(hour: Int, min: Int): ClockAngle {

        if (hour !in 0..23 || min !in 0..59) {
            throw InvalidTimeFormatException("Invalid hours or minutes value")
        }

        val minAngle = (min * MINUTE_ANGLE + MINUTE_DELTA_ANGLE).toInt()
        val hourAngle = (hour % QUANTITY_HOURS_DAY * HOUR_ANGLE + HOUR_DELTA_ANGLE * min).toInt()

        var angle = hourAngle - minAngle

        if (angle < 0) angle += 360

        return ClockAngle(angle, hourAngle, minAngle)
    }

    companion object {
        private const val QUANTITY_HOURS_DAY = 24
        private const val HOUR_ANGLE = 360.0 / QUANTITY_HOURS_DAY
        private const val MINUTE_ANGLE = 360.0 / 60
        private const val HOUR_DELTA_ANGLE = HOUR_ANGLE / 60
        private const val MINUTE_DELTA_ANGLE = MINUTE_ANGLE / 60
    }
}