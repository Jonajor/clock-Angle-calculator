package input

interface TimeInputProvider {
    fun getTime(hourMinute: String): Pair<Int, Int>
}

class ConsoleTimeInputProvider : TimeInputProvider {
    override fun getTime(hourMinute: String): Pair<Int, Int> {
        try {
            val parts = hourMinute.split(Regex("[:\\-/ˆ; ]"))
            val hour = parts[0].toInt()
            val minute = parts[1].toInt()
            return Pair(hour, minute)
        } catch (ex: Exception) {
            throw InvalidTimeFormatException("Invalid time format")
        }
    }
}
