package model

data class ClockAngle(
    var angle: Double = 0.0,
    var hourAngle: Double = 0.0,
    var minAngle: Double = 0.0
) {
    init {
        require(angle in 0.0..360.0) { "angle should be between 0 and 360" }
    }

    override fun toString(): String {
        return "Hour angle is $hourAngle, Minute angle is $minAngle"
    }
}