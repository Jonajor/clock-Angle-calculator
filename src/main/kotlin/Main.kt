import input.ConsoleTimeInputProvider
import service.ClockService
import ui.Menu

fun main(args: Array<String>) {
    val clockService = ClockService(ConsoleTimeInputProvider())

    val clockAngles = args.takeUnless { it.isEmpty() }?.let {
        args.toMutableList()
    } ?: listOf(Menu().userMenu()).toMutableList()

    clockAngles.forEach { println(clockService.handleHourAndMinute(it).toString()) }
}