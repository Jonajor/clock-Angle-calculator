package ui

import java.util.*

class Menu {

    fun userMenu(): String {
        println("Allowed format: ")
        println("HH MM (ex: 08 15 or 14 30)")
        println("HH:MM (ex: 08:15 or 14:30)")
        println("HH-MM (ex: 08-15 or 14-30)")
        println("HH/MM (ex: 08/15 or 14/30)")
        println("HHˆMM (ex: 08ˆ15 or 14ˆ30)")
        println("HH;MM (ex: 08;15 or 14;30)")
        println("Enter hour and minute information: ")
        val input = Scanner(System.`in`)
        return input.nextLine()
    }
}