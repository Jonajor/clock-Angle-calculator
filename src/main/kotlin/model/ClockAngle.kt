package model

data class ClockAngle(
    var angle: Int = 0,
    var hourAngle: Int = 0,
    var minAngle: Int = 0
) {
    init {
        require(angle in 0..360) { "angle should be between 0 and 360" }
    }

    override fun toString(): String {
        return "Hour angle is $hourAngle, Minute angle is $minAngle"
    }
}